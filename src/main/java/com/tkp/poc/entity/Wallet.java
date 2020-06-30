package com.tkp.poc.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tkp.poc.common.constant.EntityConstants;
import com.tkp.poc.entity.listener.EntityListener;

@Entity
@Table(name = "user_wallet")
@EntityListeners(EntityListener.class)
public class Wallet extends AuditableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = EntityConstants.COLUMN_ID)
	private Long id;

	@Column(name = EntityConstants.COLUMN_AMOUNT)
	private BigDecimal amount;

	@OneToOne(mappedBy = "wallet")
	private User user;

	@OneToMany(mappedBy = "wallet")
	private Set<WalletTransaction> walletTransactions;

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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the walletTransactions
	 */
	public Set<WalletTransaction> getWalletTransactions() {
		return walletTransactions;
	}

	/**
	 * @param walletTransactions the walletTransactions to set
	 */
	public void setWalletTransactions(Set<WalletTransaction> walletTransactions) {
		this.walletTransactions = walletTransactions;
	}

	
}
