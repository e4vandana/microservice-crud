package io.github.vandana.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_accounts")
public class UserAccounts extends AbstractBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5203726226963473520L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;

	@Column(name = "account_number", nullable = false)
	private Long accountNumber;

	@Column(name = "account_type", nullable = false)
	private String accountType;

	@Column(name = "bank_name", nullable = false)
	private String bankName;

	@Column(name = "branch_name", nullable = false)
	private String branchName;

	@Column(name = "balance", nullable = false)
	private Double balance;

	@Column(name = "account_status", nullable = false)
	private String accountStatus;
	
	public UserAccounts() {
		
	}

	public UserAccounts(Long accountNumber, String accountType, String bankName,
			String branchName, Double balance, String accountStatus) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.bankName = bankName;
		this.branchName = branchName;
		this.balance = balance;
		this.accountStatus = accountStatus;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

}
