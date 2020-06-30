package com.tkp.poc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserTransactionHistory {
	private BigDecimal currentAmount;
	private List<TransactionDetail> transactions = new ArrayList<>();
	/**
	 * @return the currentAmount
	 */
	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}
	/**
	 * @param currentAmount the currentAmount to set
	 */
	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}
	/**
	 * @return the transactions
	 */
	public List<TransactionDetail> getTransactions() {
		return transactions;
	}
	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<TransactionDetail> transactions) {
		this.transactions = transactions;
	}
	
	
}
