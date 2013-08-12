package com.jsteadman.BankingManager;

public class AccountTransactions {

	protected String accountNumber;
	protected String transactionDate;
	protected double checkingBalance;
	protected double savingsBalance;

	// sets and retrieves the accoungNumber
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	// sets and retrieves the transactionDate
	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	// sets and retrieves checkingBalance
	public double getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingBalance(double checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	// sets and retrieves savingsBalance
	public double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

}