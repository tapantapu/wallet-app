package com.tkp.poc.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tkp.poc.common.constant.EntityConstants;
import com.tkp.poc.entity.listener.EntityListener;

@Entity
@Table(name = "user_wallet_txn_history")
@EntityListeners(EntityListener.class)
public class WalletTransactionHistory extends AuditableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = EntityConstants.COLUMN_ID)
	private Long id;

	@Column(name = EntityConstants.COLUMN_TXN_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionTs;

	@Column(name = EntityConstants.COLUMN_OLD_AMOUNT)
	private BigDecimal oldAmount;
	@Column(name = EntityConstants.COLUMN_NEW_AMOUNT)
	private BigDecimal newAmount;
	@Column(name = EntityConstants.COLUMN_TRANSACTED_AMOUNT)
	private BigDecimal tranactedAmount;

	@OneToOne(mappedBy = "transactionHistory")
	private WalletTransaction walletTransaction;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User transactedTo;

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
	 * @return the transactionTs
	 */
	public Date getTransactionTs() {
		return transactionTs;
	}

	/**
	 * @param transactionTs the transactionTs to set
	 */
	public void setTransactionTs(Date transactionTs) {
		this.transactionTs = transactionTs;
	}

	/**
	 * @return the oldAmount
	 */
	public BigDecimal getOldAmount() {
		return oldAmount;
	}

	/**
	 * @param oldAmount the oldAmount to set
	 */
	public void setOldAmount(BigDecimal oldAmount) {
		this.oldAmount = oldAmount;
	}

	/**
	 * @return the newAmount
	 */
	public BigDecimal getNewAmount() {
		return newAmount;
	}

	/**
	 * @param newAmount the newAmount to set
	 */
	public void setNewAmount(BigDecimal newAmount) {
		this.newAmount = newAmount;
	}

	/**
	 * @return the tranactedAmount
	 */
	public BigDecimal getTranactedAmount() {
		return tranactedAmount;
	}

	/**
	 * @param tranactedAmount the tranactedAmount to set
	 */
	public void setTranactedAmount(BigDecimal tranactedAmount) {
		this.tranactedAmount = tranactedAmount;
	}

	/**
	 * @return the walletTransaction
	 */
	public WalletTransaction getWalletTransaction() {
		return walletTransaction;
	}

	/**
	 * @param walletTransaction the walletTransaction to set
	 */
	public void setWalletTransaction(WalletTransaction walletTransaction) {
		this.walletTransaction = walletTransaction;
	}

	/**
	 * @return the transactedTo
	 */
	public User getTransactedTo() {
		return transactedTo;
	}

	/**
	 * @param transactedTo the transactedTo to set
	 */
	public void setTransactedTo(User transactedTo) {
		this.transactedTo = transactedTo;
	}

}
