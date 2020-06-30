package com.tkp.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkp.poc.entity.WalletTransactionHistory;

@Repository
public interface WalletTransactionHistoryRepository extends JpaRepository<WalletTransactionHistory, Long> {

}
