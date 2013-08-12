package com.jsteadman.BankingManager;

public class Checking extends TransactionLog implements Itransaction {

	private final double CHECKING_FEE = 0.02;
	private final double OVERDRAFT_FEE = 25;

	double ogCheckingBalance;
	double newCheckingBalance;

	@Override
	public void credit(double deposit) {
		ogCheckingBalance = getCheckingBalance();
		newCheckingBalance = (ogCheckingBalance + deposit) - CHECKING_FEE;
		setCheckingBalance(newCheckingBalance);
		writeFile("original checking balance: " + ogCheckingBalance,
				"checking withdrawal: " + deposit, "new checking balance: "
						+ newCheckingBalance);
	}

	@Override
	public void debit(double withdrawal) {

		// String date = getTransactionDate();
		ogCheckingBalance = getCheckingBalance();
		newCheckingBalance = (ogCheckingBalance - withdrawal) - CHECKING_FEE;
		if (withdrawal > ogCheckingBalance) {
			newCheckingBalance = (ogCheckingBalance - withdrawal)
					- (CHECKING_FEE + OVERDRAFT_FEE);
		}
		setCheckingBalance(newCheckingBalance);
		// log.writeFile(null);
		writeFile("original checking balance: " + ogCheckingBalance,
				"checking withdrawal: " + withdrawal, "new checking balance: "
						+ newCheckingBalance);
	}

	public void date(String date) {

	}

}