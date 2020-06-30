package com.tkp.poc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkp.poc.common.constant.TransactionType;
import com.tkp.poc.common.factory.ThreadContainer;
import com.tkp.poc.entity.User;
import com.tkp.poc.entity.Wallet;
import com.tkp.poc.entity.WalletTransaction;
import com.tkp.poc.entity.WalletTransactionHistory;
import com.tkp.poc.model.TransactionDetail;
import com.tkp.poc.model.TransactionRequest;
import com.tkp.poc.model.TransactionResponse;
import com.tkp.poc.model.UserTransactionHistory;
import com.tkp.poc.repository.UserRepository;
import com.tkp.poc.repository.WalletRepository;
import com.tkp.poc.repository.WalletTransactionHistoryRepository;
import com.tkp.poc.repository.WalletTransactionRepository;

@Service
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private WalletTransactionHistoryRepository walletTransactionHistoryRepository;

	@Autowired
	private WalletTransactionRepository walletTransactionRepository;

	@Autowired
	private UserRepository userRepository;

	public TransactionResponse amountOperation(@Valid TransactionRequest transactionRequest) {

		String transactionId = UUID.randomUUID().toString();

		// Add money to self
		if (ThreadContainer.getUserContext().getUserName().equalsIgnoreCase(transactionRequest.getToUser())) {
			return addAmountToSelf(transactionRequest, transactionId);
		}

		return transferMoney(transactionRequest, transactionId);
	}

	public TransactionResponse transferMoney(@Valid TransactionRequest transactionRequest, String transactionId) {

		User user = userRepository.findByUserName(transactionRequest.getToUser());

		if (Objects.isNull(user)) {
			throw new RuntimeException("Amount cannot be transfer to the user as it is not present");
		}

		createDebitTransaction( ThreadContainer.getUserContext(),transactionRequest, transactionId);
		createCreditTransaction(user, transactionRequest, transactionId);
		return null;
	}

	private void createCreditTransaction(User user, @Valid TransactionRequest transactionRequest,
			String transactionId) {
		
		Wallet  wallet = findWalletByUser(user);
		WalletTransaction walletTransaction = createWalletTransaction(wallet, transactionId);
		walletTransaction.setStatus(TransactionType.CREDIT);
		WalletTransactionHistory transactionHistory = createTransactionHistory(wallet,transactionRequest,
				user, TransactionType.CREDIT);
		walletTransaction.setTransactionHistory(transactionHistory);
//		walletTransaction = walletTransactionRepository.save(walletTransaction);
		wallet.setAmount(transactionHistory.getNewAmount());
		if (wallet.getWalletTransactions() == null) {
			wallet.setWalletTransactions(new HashSet<>());
		}

		wallet.setAmount(transactionHistory.getNewAmount());
		wallet.getWalletTransactions().add(walletTransactionRepository.save(walletTransaction));

		walletRepository.save(wallet);
	}

	private void createDebitTransaction(User user, @Valid TransactionRequest transactionRequest,
			String transactionId) {
		
		Wallet wallet = findWalletByUser(user);
		WalletTransaction walletTransaction = createWalletTransaction(wallet, transactionId);
		walletTransaction.setStatus(TransactionType.DEBIT);

		WalletTransactionHistory transactionHistory = createTransactionHistory(wallet,transactionRequest,
				ThreadContainer.getUserContext(), TransactionType.DEBIT);

		walletTransaction.setTransactionHistory(transactionHistory);

		walletTransaction = walletTransactionRepository.save(walletTransaction);

		wallet.setAmount(transactionHistory.getNewAmount());

		if (wallet.getWalletTransactions() == null) {
			wallet.setWalletTransactions(new HashSet<>());
		}

		wallet.setAmount(transactionHistory.getNewAmount());
		wallet.getWalletTransactions().add(walletTransactionRepository.save(walletTransaction));

		walletRepository.save(wallet);
	}

	private WalletTransaction createWalletTransaction(Wallet wallet, String transactionId) {
		WalletTransaction walletTransaction = new WalletTransaction();
		walletTransaction.setWallet(wallet);
		walletTransaction.setTransactionId(transactionId);
		return walletTransaction;
	}

	@Transactional
	public TransactionResponse addAmountToSelf(@Valid TransactionRequest transactionRequest, String transactionId) {

		User user = ThreadContainer.getUserContext();

		Wallet wallet = findWalletByUser(user);

		// Creating Transaction
		WalletTransaction walletTransaction = createWalletTransaction(wallet, transactionId);

		WalletTransactionHistory walletTransactionHistory = createTransactionHistory(wallet, transactionRequest, user,
				TransactionType.CREDIT);
		walletTransaction.setTransactionHistory(walletTransactionHistoryRepository.save(walletTransactionHistory));
		walletTransaction.setStatus(TransactionType.CREDIT);

		if (wallet.getWalletTransactions() == null) {
			wallet.setWalletTransactions(new HashSet<>());
		}

		wallet.setAmount(walletTransactionHistory.getNewAmount());
		wallet.getWalletTransactions().add(walletTransactionRepository.save(walletTransaction));

		walletRepository.save(wallet);

		return createSuccessTransactionResponse(transactionId);
	}

	private Wallet findWalletByUser(User user) {
		Optional<Wallet> optionalWallet = walletRepository.findById(user.getWallet().getId());

		if (!optionalWallet.isPresent()) {
			// TODO needs to add customize exception
			throw new RuntimeException("There is not wallet configure for user");
		}

		Wallet wallet = optionalWallet.get();
		return wallet;
	}

	// Creating the transactionHistory
	private WalletTransactionHistory createTransactionHistory(Wallet wallet,TransactionRequest transactionRequest, User user,
			TransactionType transactionType) {
		WalletTransactionHistory walletTransactionHistory = new WalletTransactionHistory();
		walletTransactionHistory.setTransactionTs(new Date());
		createAmountOperation(wallet,walletTransactionHistory, transactionRequest, transactionType);
		walletTransactionHistory.setTransactedTo(user);
		return walletTransactionHistory;
	}

	private void createAmountOperation(Wallet wallet, WalletTransactionHistory walletTransactionHistory,
			TransactionRequest transactionRequest, TransactionType transactionType) {

		switch (transactionType) {
		case CREDIT:
			walletTransactionHistory
					.setNewAmount(wallet.getAmount().add(transactionRequest.getAmount()));
			walletTransactionHistory.setTranactedAmount(transactionRequest.getAmount());
			walletTransactionHistory.setOldAmount(wallet.getAmount());
			break;

		case DEBIT:

			BigDecimal transactedAmount = wallet.getAmount()
					.subtract(transactionRequest.getAmount());
			if (transactedAmount.compareTo(BigDecimal.ZERO) < 0) {
				// TODO Customized exception
				throw new RuntimeException("Insufficient balance");
			}

			walletTransactionHistory.setNewAmount(transactedAmount);
			walletTransactionHistory.setTranactedAmount(transactionRequest.getAmount());
			walletTransactionHistory.setOldAmount(ThreadContainer.getUserContext().getWallet().getAmount());
			break;
		}
	}

	private TransactionResponse createSuccessTransactionResponse(String transactionId) {
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setStatus("success");
		transactionResponse.setTransactionId(transactionId);
		return transactionResponse;
	}

	@Transactional
	public UserTransactionHistory getPassbook() {
		
		User user = ThreadContainer.getUserContext();
		Wallet wallet = findWalletByUser(user);


		UserTransactionHistory userTransactionHistory = new UserTransactionHistory();
		userTransactionHistory.setCurrentAmount(wallet.getAmount());

		List<TransactionDetail> transactionDetails = wallet.getWalletTransactions().stream()
				.map(txn -> new TransactionDetail(txn.getStatus(), txn.getTransactionHistory().getTranactedAmount(),
						txn.getTransactionHistory().getTransactedTo().getUserName(),
						txn.getTransactionHistory().getTransactionTs(), txn.getTransactionId()))
				.collect(Collectors.toList());

		userTransactionHistory.setTransactions(transactionDetails);

		return userTransactionHistory;
	}

}
