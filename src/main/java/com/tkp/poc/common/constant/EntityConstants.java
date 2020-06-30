package com.tkp.poc.common.constant;

public final class EntityConstants {

	public EntityConstants() throws IllegalAccessException {
		throw new IllegalAccessException("This object cannot be instantiate");
	}
	
	
	public static final String CREATED_BY = "created_by";
	public static final String CREATED_TS = "created_ts";
	public static final String UPDATED_BY = "updated_by";
	public static final String UPDATED_TS = "updated_ts";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_FIRST_NAME = "first_name";
	public static final String COLUMN_LAST_NAME = "last_name";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "passsword";
	public static final String COLUMN_LAST_LOGIN_TS = "last_login_ts";
	public static final String COLUMN_AMOUNT = "amount";
	public static final String COLUMN_WALLET_STATUS = "status";
	public static final String JOIN_COLUMN_WALLET = "wallet_id";
	public static final String COLUMN_TXN_DATE = "wallet_txn_ts";
	public static final String COLUMN_OLD_AMOUNT = "wallet_old_amt";
	public static final String COLUMN_NEW_AMOUNT = "wallet_new_amt";
	public static final String COLUMN_TRANSACTED_AMOUNT = "wallet_txn_amt";
	public static final String COLUMN_TRANSACTION_ID = "txn_id";
	

}
