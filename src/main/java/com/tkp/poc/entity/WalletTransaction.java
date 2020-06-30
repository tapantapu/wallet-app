package com.tkp.poc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tkp.poc.common.constant.EntityConstants;
import com.tkp.poc.common.constant.TransactionType;
import com.tkp.poc.entity.listener.EntityListener;

@Entity
@Table(name = "user_wallet_txn")
@EntityListeners(EntityListener.class)
public class WalletTransaction extends AuditableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = EntityConstants.COLUMN_ID)
	private Long id;
	
	@Column(name = EntityConstants.COLUMN_WALLET_STATUS)
	@Enumerated(value = EnumType.STRING)
	private TransactionType status;
	

	@Column(name = EntityConstants.COLUMN_TRANSACTION_ID,nullable = false)
	private String transactionId;
	
	
	@ManyToOne
    @JoinColumn(name=EntityConstants.JOIN_COLUMN_WALLET, nullable=false)
	private Wallet wallet;


	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_txn_id", referencedColumnName = "id")
	private WalletTransactionHistory transactionHistory;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the status
	 */
	public TransactionType getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(TransactionType status) {
		this.status = status;
	}


	/**
	 * @return the wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}


	/**
	 * @param wallet the wallet to set
	 */
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}


	/**
	 * @return the transactionHistory
	 */
	public WalletTransactionHistory getTransactionHistory() {
		return transactionHistory;
	}


	/**
	 * @param transactionHistory the transactionHistory to set
	 */
	public void setTransactionHistory(WalletTransactionHistory transactionHistory) {
		this.transactionHistory = transactionHistory;
	}


	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}


	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}
