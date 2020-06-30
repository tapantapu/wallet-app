## Api requirements and running instructions
1. Connect to the MySQL server and create database -  wallet.

 ```
The schema and database detailes to be configured in the
``` 
wallet-app/src/main/resources/application.properties
```
## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url = jdbc:mysql://localhost:3306/wallet-db?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username = root
spring.datasource.password = root


## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = create-drop


2. Tables  Structures

select * from `wallet-db`.`user` u ;
select * from `wallet-db`.user_wallet uw ;
select * from `wallet-db`.user_wallet_txn uwt ;
select * from `wallet-db`.user_wallet_txn_history uwth ;

-- `wallet-db`.`user` definition

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_login_ts` datetime DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `passsword` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `wallet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKg1j9nf3d8g7f8wkuhewidxuei` (`wallet_id`),
  CONSTRAINT `FKg1j9nf3d8g7f8wkuhewidxuei` FOREIGN KEY (`wallet_id`) REFERENCES `user_wallet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- `wallet-db`.user_wallet definition

CREATE TABLE `user_wallet` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- `wallet-db`.user_wallet_txn definition

CREATE TABLE `user_wallet_txn` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `txn_id` varchar(255) NOT NULL,
  `wallet_txn_id` bigint(20) DEFAULT NULL,
  `wallet_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs8updvd5vh9mh435dkj20lmk4` (`wallet_txn_id`),
  KEY `FKmj3fewuquijy4j3jc4wh6fmfk` (`wallet_id`),
  CONSTRAINT `FKmj3fewuquijy4j3jc4wh6fmfk` FOREIGN KEY (`wallet_id`) REFERENCES `user_wallet` (`id`),
  CONSTRAINT `FKs8updvd5vh9mh435dkj20lmk4` FOREIGN KEY (`wallet_txn_id`) REFERENCES `user_wallet_txn_history` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- `wallet-db`.user_wallet_txn_history definition

CREATE TABLE `user_wallet_txn_history` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_ts` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_ts` datetime DEFAULT NULL,
  `wallet_new_amt` decimal(19,2) DEFAULT NULL,
  `wallet_old_amt` decimal(19,2) DEFAULT NULL,
  `wallet_txn_amt` decimal(19,2) DEFAULT NULL,
  `wallet_txn_ts` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5v1e91fslqchnx1y7u65iinbx` (`user_id`),
  CONSTRAINT `FK5v1e91fslqchnx1y7u65iinbx` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

``` 
3. Start application by:
``` 
mvn spring-boot:run
``` 
All tables, reference data and some dummy data will be created and inserted by Flyway.

``` 
## Api endpoints

Examples of all requests can be found in:
``` 

1. http://localhost:8080/users  (Create User Request)

Body:
{
	"firstName" : "tapan",
	"lastName" : "parida",
	"userName" : "tkp1235",
	"password" : "abc123"
}

Response:
{
    "createdBy": "default_created_User",
    "createdTs": "2020-06-28T18:42:08.416+00:00",
    "updatedBy": null,
    "updatedTs": null,
    "id": 1,
    "firstName": "tapan",
    "lastName": "parida",
    "userName": "tkp1235",
    "password": "abc",
    "lastLoginTs": null,
    "wallet": {
        "createdBy": "default_created_User",
        "createdTs": "2020-06-28T18:42:08.442+00:00",
        "updatedBy": null,
        "updatedTs": null,
        "id": 2,
        "amount": 0,
        "user": null,
        "walletTransactions": null
    }
}

2. http://localhost:8080/wallets (Add Amount in Wallet Request)


{
	"toUser" : "tkp1235",
	"amount" : 600,
	"transactionType" : "CREDIT"
}


Response:
{
    "transactionId": "2e739c5b-7fae-4166-a0a7-314ee5ac2c6b",
    "status": "success"
}

DB data:
2	default_created_User	2020-06-28 18:42:08.0	tkp1235	2020-06-28 18:47:27.0	600.00


3. http://localhost:8080/wallets/passbook  (Check Passbook Request)


{
    "currentAmount": 600,
    "transactions": [
        {
            "type": "CREDIT",
            "amount": 600,
            "source": "tkp1235",
            "date": "2020-06-28T18:47:27.000+00:00",
            "transactionId": "2e739c5b-7fae-4166-a0a7-314ee5ac2c6b"
        }
    ]
}

4. http://localhost:8080/wallets (Transfor amount one account (tkp1235) to other account(tkp123456) Request)


{
	"toUser" : "tkp123456",
	"amount" : 50,
	"transactionType" : "DEBIT"
}




