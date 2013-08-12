package com.jsteadman.BankingManager;

public class Savings extends TransactionLog implements Itransaction {

	private final double INTEREST_RATE = 1.005;

	double ogSavingsBalance;
	double newSavingsBalance;

	@Override
	public void credit(double deposit) {
		ogSavingsBalance = getSavingsBalance();
		newSavingsBalance = (ogSavingsBalance + deposit) * INTEREST_RATE;
		setSavingsBalance(newSavingsBalance);
		writeFile("original savings balance: " + ogSavingsBalance,
				"savings withdrawal: " + deposit, "new savings balance: "
						+ newSavingsBalance);

	}

	@Override
	public void debit(double withdrawal) {
		ogSavingsBalance = getSavingsBalance();
		newSavingsBalance = (ogSavingsBalance - withdrawal) * INTEREST_RATE;
		setSavingsBalance(newSavingsBalance);
		writeFile("original savings balance: " + ogSavingsBalance,
				"savings withdrawal: " + withdrawal, "new savings balance: "
						+ newSavingsBalance);

	}

}