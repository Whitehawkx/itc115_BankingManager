package com.jsteadman.BankingManager;

public class Checking extends TransactionLog implements Itransaction {

	private final double CHECKING_FEE = 0.02;
	private final double OVERDRAFT_FEE = 25;

	double ogCheckingBalance;
	double newCheckingBalance;

	// This handles deposits made into the checking account
	@Override
	public void credit(double deposit) {
		ogCheckingBalance = getCheckingBalance();
		newCheckingBalance = (ogCheckingBalance + deposit) - CHECKING_FEE;
		// set new checking balance
		setCheckingBalance(newCheckingBalance);
		// write original balance, deposit amount and new balance to
		// the .txt file
		writeFile("original checking balance: " + ogCheckingBalance,
				"checking withdrawal: " + deposit, "new checking balance: "
						+ newCheckingBalance);
	}

	// This handles withdrawals made from the checking account
	@Override
	public void debit(double withdrawal) {
		// get the checking balance and set as new variable
		ogCheckingBalance = getCheckingBalance();
		newCheckingBalance = (ogCheckingBalance - withdrawal) - CHECKING_FEE;
		if (withdrawal > ogCheckingBalance) {
			newCheckingBalance = (ogCheckingBalance - withdrawal)
					- (CHECKING_FEE + OVERDRAFT_FEE);
		}
		// set new checking balance
		setCheckingBalance(newCheckingBalance);
		// write original balance, withdrawal amount and new balance to
		// the .txt file
		writeFile("original checking balance: " + ogCheckingBalance,
				"checking withdrawal: " + withdrawal, "new checking balance: "
						+ newCheckingBalance);
	}

}