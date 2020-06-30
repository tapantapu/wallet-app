package com.tkp.poc.model;

import java.math.BigDecimal;
import java.util.Date;

import com.tkp.poc.common.constant.TransactionType;

public class TransactionDetail {
	private TransactionType type;
	private BigDecimal amount;
	private String source;
	private Date date;
	private String transactionId;
	public TransactionDetail(TransactionType type, BigDecimal amount, String source, Date date, String transactionId) {
		super();
		this.type = type;
		this.amount = amount;
		this.source = source;
		this.date = date;
		this.setTransactionId(transactionId);
	}
	/**
	 * @return the type
	 */
	public TransactionType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(TransactionType type) {
		this.type = type;
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
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	

}
