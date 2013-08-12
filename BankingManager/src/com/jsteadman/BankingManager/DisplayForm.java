package com.jsteadman.BankingManager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DisplayForm {

	// declare all the things!
	private JFrame frame;
	private JPanel panel;
	private JFrame acctFrame;
	private JPanel acctPanel;
	private JLabel lblCurrentBalance;
	protected JRadioButton radioAccountChecking;
	protected JRadioButton radioAccountSavings;
	private JRadioButton radioActionDebit;
	private JRadioButton radioActionCredit;
	private JTextField txtAmount;
	private JButton btnSubmit;
	private JButton btnGetBalance;
	private JButton btnShowLog;
	private JButton btnExit;
	private JButton btnCreateAcct;
	private JButton btnEnterAcct;
	private JTextField txtAcct;
	private JFrame balanceFrame;
	private JPanel balancePanel;
	private JButton btnSetBalance;
	private JButton btnEnterBalance;
	private JTextField txtCheckingBalance;
	private JTextField txtSavingsBalance;

	String timeStamp;

	// declare checking and savings classes
	private Checking checking = new Checking();
	private Savings savings = new Savings();

	// create the initial frame
	public DisplayForm() {
		createFrame();
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setSize(600, 600);
		createPanel();
		frame.add(panel);
		frame.setVisible(true);
	}

	// add all the things!
	private void createPanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 5, 4));

		lblCurrentBalance = new JLabel();
		radioAccountChecking = new JRadioButton("Checking");
		radioAccountSavings = new JRadioButton("Savings");
		radioActionDebit = new JRadioButton("Debit");
		radioActionCredit = new JRadioButton("Credit");
		txtAmount = new JTextField();
		btnSubmit = new JButton("Submit Amount");
		btnSubmit.addActionListener(new SubmitAmount());
		btnGetBalance = new JButton("View Balance");
		btnGetBalance.addActionListener(new ViewBalance());
		btnShowLog = new JButton("View Transaction History");
		btnShowLog.addActionListener(new ViewTransactionHistory());
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ExitEvent());
		btnCreateAcct = new JButton("Enter New Account Number (1st)");
		btnCreateAcct.addActionListener(new AddAccountNumber());
		btnSetBalance = new JButton("Set Starting Balances (2nd)");
		btnSetBalance.addActionListener(new StartingBalance());

		ButtonGroup group1 = new ButtonGroup();
		group1.add(radioAccountChecking);
		group1.add(radioAccountSavings);

		ButtonGroup group2 = new ButtonGroup();
		group2.add(radioActionDebit);
		group2.add(radioActionCredit);

		panel.add(radioAccountChecking);
		panel.add(radioAccountSavings);
		panel.add(radioActionDebit);
		panel.add(radioActionCredit);

		panel.add(txtAmount);
		panel.add(btnSubmit);
		panel.add(btnGetBalance);
		panel.add(lblCurrentBalance);
		panel.add(btnShowLog);
		panel.add(btnExit);
		panel.add(btnCreateAcct);
		panel.add(btnSetBalance);

	}

	/*
	 * This handles creating a new frame to enter the account name/number.
	 */
	private void accountFrame() {
		acctFrame = new JFrame();
		acctFrame.setSize(300, 300);
		createAccount();
		acctFrame.add(acctPanel);
		acctFrame.setVisible(true);
	}

	private void createAccount() {
		acctPanel = new JPanel();
		acctPanel.setLayout(new GridLayout(2, 1, 4, 4));

		btnEnterAcct = new JButton("Submit Account Number");
		btnEnterAcct.addActionListener(new SubmitAccountNumber());
		txtAcct = new JTextField("");

		acctPanel.add(btnEnterAcct);
		acctPanel.add(txtAcct);

	}

	/*
	 * When called this creates a new frame for entering the starting balances
	 * for checking and savings.
	 */
	private void balanceFrame() {
		balanceFrame = new JFrame();
		balanceFrame.setSize(300, 300);
		setStartingBalance();
		balanceFrame.add(balancePanel);
		balanceFrame.setVisible(true);
	}

	private void setStartingBalance() {
		balancePanel = new JPanel();
		balancePanel.setLayout(new GridLayout(3, 1, 4, 4));

		btnEnterBalance = new JButton("Submit Starting Balances");
		btnEnterBalance.addActionListener(new SubmitInitialBalance());
		txtCheckingBalance = new JTextField("Checking");
		txtCheckingBalance.addFocusListener(new focusGained());
		txtSavingsBalance = new JTextField("Savings");
		txtSavingsBalance.addFocusListener(new focusGained());

		balancePanel.add(btnEnterBalance);
		balancePanel.add(txtCheckingBalance);
		balancePanel.add(txtSavingsBalance);

	}

	/*
	 * When the JTextFields for the setting the initial balances gain focus,
	 * select all of the text for easy overwriting.
	 */
	private class focusGained implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			if (txtCheckingBalance.hasFocus()) {
				txtCheckingBalance.selectAll();
			} else if (txtSavingsBalance.hasFocus()) {
				txtSavingsBalance.selectAll();
			}

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// never used
		}

	}

	/*
	 * Button action which then calls the methods to create the frame for
	 * inputting the initial account balances.
	 */
	private class StartingBalance implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// don't allow a balance to be set until after
			// an account number has been entered
			if (checking.getAccountNumber() != null) {
				balanceFrame();
			}
		}

	}

	/*
	 * When the initial balances of submitted, they are set in the
	 * AccountTransactions class for both checking and savings.
	 */
	private class SubmitInitialBalance implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				checking.setCheckingBalance(Double
						.parseDouble(txtCheckingBalance.getText()));
				savings.setSavingsBalance(Double.parseDouble(txtSavingsBalance
						.getText()));
				// remove the button
				panel.remove(btnSetBalance);
				// revalidate the panel
				panel.revalidate();
				// overkill
				panel.repaint();
			} catch (NumberFormatException e) {
				e.getMessage();
			}

			// close the window
			balanceFrame.dispose();
		}
	}

	/*
	 * Action for exiting the program.
	 */
	private class ExitEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// exit the program
			System.exit(0);

		}

	}

	/*
	 * Button action which calls the methods to create the new frame for
	 * entering the account name/number.
	 */
	private class AddAccountNumber implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			accountFrame();

		}

	}

	/*
	 * When account name has been submitted, it is set in the AccountTransaction
	 * class for both checking and savings.
	 */
	private class SubmitAccountNumber implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// don't allow the account number to be blank
			if (!txtAcct.getText().equals("")) {
				// send the account number through both child classes
				// to ensure both can see and access it
				checking.setAccountNumber(txtAcct.getText());
				savings.setAccountNumber(txtAcct.getText());

				// remove the button
				panel.remove(btnCreateAcct);
				// revalidate the panel
				panel.revalidate();
				// overkill
				panel.repaint();

			}
			// create the .txt file immediately
			checking.writeFile(null, null, null);
			// close the window
			acctFrame.dispose();
		}
	}

	/*
	 * This handles the action performed when making a withdrawal or deposit
	 * from either checking or savings.
	 */
	private class SubmitAmount implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// null check here to prevent crashing if no value is present
			if (!txtAmount.getText().equals("")) {
				// don't crash if something other than a number is entered
				try {

					double deposit = Double.parseDouble(txtAmount.getText());
					double withdrawal = Double.parseDouble(txtAmount.getText());

					timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
							.format(Calendar.getInstance().getTime());

					// The date for the transaction needs to be set
					// before the values are sent to the checking class
					checking.setTransactionDate(timeStamp);
					savings.setTransactionDate(timeStamp);
					// Send appropriate values based on radio buttons selected
					if (radioAccountChecking.isSelected()) {
						if (radioActionCredit.isSelected()) {
							checking.credit(deposit);
						} else if (radioActionDebit.isSelected()) {
							checking.debit(withdrawal);
						}

					} else if (radioAccountSavings.isSelected()) {
						if (radioActionCredit.isSelected()) {
							savings.credit(deposit);
						} else if (radioActionDebit.isSelected()) {
							savings.debit(withdrawal);
						}
					}
				} catch (NumberFormatException e) {
					e.getMessage();
				}

			}
			// reset the field to blank
			txtAmount.setText("");

		}

	}

	/*
	 * This allows the user to view their current checking or savings balance at
	 * any time.
	 */
	private class ViewBalance implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// probably not necessary, but make the field
			// blank before writing new value to it
			lblCurrentBalance.setText("");

			if (radioAccountChecking.isSelected()) {
				lblCurrentBalance.setText("Current Checking Balance: $"
						+ (new DecimalFormat("##.##").format(checking
								.getCheckingBalance())));

			} else if (radioAccountSavings.isSelected()) {
				lblCurrentBalance.setText("Current Savings Balance: $"
						+ (new DecimalFormat("##.##").format(savings
								.getSavingsBalance())));
			}
		}

	}

	/*
	 * This method reads the stored .txt file and displays it on the console.
	 */
	private class ViewTransactionHistory implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			checking.readFile();

		}

	}

}