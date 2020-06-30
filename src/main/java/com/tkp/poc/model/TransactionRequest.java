package com.tkp.poc.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.tkp.poc.common.constant.TransactionType;

public class TransactionRequest {
	
	@NotNull private String toUser;
	@NotNull private BigDecimal amount;
	@NotNull private TransactionType transactionType;
	/**
	 * @return the toUser
	 */
	public String getToUser() {
		return toUser;
	}
	/**
	 * @param toUser the toUser to set
	 */
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the transactionType
	 */
	public TransactionType getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	

}
