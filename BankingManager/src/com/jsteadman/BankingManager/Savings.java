package com.jsteadman.BankingManager;

public class Savings extends TransactionLog implements Itransaction {

	private final double INTEREST_RATE = 1.005;

	double ogSavingsBalance;
	double newSavingsBalance;

	// Handles deposits made into savings account.
	@Override
	public void credit(double deposit) {
		// get savings balance and set as new variable
		ogSavingsBalance = getSavingsBalance();
		// do some silly math
		newSavingsBalance = (ogSavingsBalance + deposit) * INTEREST_RATE;
		// set new savings balance
		setSavingsBalance(newSavingsBalance);
		// write the original balance, withdrawal amount and new balance to
		// the .txt file
		writeFile("original savings balance: " + ogSavingsBalance,
				"savings withdrawal: " + deposit, "new savings balance: "
						+ newSavingsBalance);

	}

	// Handles withdrawals made from savings account.
	@Override
	public void debit(double withdrawal) {
		// get savings balance and set as new variable
		ogSavingsBalance = getSavingsBalance();
		// silly math
		newSavingsBalance = (ogSavingsBalance - withdrawal) * INTEREST_RATE;
		// set new savings balance
		setSavingsBalance(newSavingsBalance);
		// write the original balance, withdrawal amount and new balance to
		// the .txt file
		writeFile("original savings balance: " + ogSavingsBalance,
				"savings withdrawal: " + withdrawal, "new savings balance: "
						+ newSavingsBalance);

	}

}