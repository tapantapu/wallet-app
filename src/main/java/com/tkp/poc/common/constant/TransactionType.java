/**
 * 
 */
package com.tkp.poc.common.constant;

public enum TransactionType {
	
	CREDIT("Credit") , DEBIT("Debit");
	
	private String type;
	
	TransactionType(String type){
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
