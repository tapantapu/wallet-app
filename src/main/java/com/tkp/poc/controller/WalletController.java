/**
 * 
 */
package com.tkp.poc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tkp.poc.model.TransactionRequest;
import com.tkp.poc.model.TransactionResponse;
import com.tkp.poc.model.UserTransactionHistory;
import com.tkp.poc.service.WalletService;

/**
 * @author etappar
 *
 */
@RestController
@RequestMapping("/wallets")
public class WalletController {

	
	@Autowired
	private WalletService walletService;
	
	@PostMapping
	public TransactionResponse amountOperation(@Valid @RequestBody TransactionRequest transactionRequest) {
		return walletService.amountOperation(transactionRequest);
	}
	
	
	@GetMapping("passbook")
	public UserTransactionHistory getPassbook() {
		return walletService.getPassbook();
	}
	
	
}
