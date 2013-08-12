package com.jsteadman.BankingManager;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TransactionLog extends AccountTransactions {

	// path where .txt file is created
	private String path = "/Users/jonathan/Documents/"; // for windows
														// "C:\\files\\";
	private PrintWriter writer;
	String fileName;

	/*
	 * This is used to create the .txt file and write to it. It takes arguments
	 * for the original balance, withdrawal/deposit and new balance.
	 */
	public void writeFile(String ogbalance, String transaction, String balance) {

		// the try catch is required when you do
		// file input and output
		try {
			startFile(path);
			addText(ogbalance, transaction, balance);
			// closeFile();
		} catch (IOException e) {
			// message would be better for user
			// than stack trace
			e.getMessage();
		}
	}

	// This could probably be removed...
	public void startFile(String path) throws IOException {
		this.path = path;
		createFile();

	}

	// creates the file
	private void createFile() throws IOException {
		fileName = getAccountNumber() + ".txt";
		// create the file, the true means append to
		// the file if it exists, creates it if it doesn't exist
		FileWriter outFile = new FileWriter(path + fileName, true);
		writer = new PrintWriter(outFile);

	}

	// Handles what is written to the .txt file
	public void addText(String ogbalance, String transaction, String balance) {
		// Don't write null values
		if (getTransactionDate() != null && ogbalance != null
				&& transaction != null && balance != null) {
			writer.println(getTransactionDate());
			writer.println(ogbalance);
			writer.println(transaction);
			writer.println(balance + "\n");
		}
		closeFile();
	}

	public void closeFile() {
		// close the file for editing
		writer.close();
	}

	public void readFile() {
		// this method reads the file
		try {
			System.out.println(getText());
		} catch (IOException e) {
			e.getMessage();
		}

	}

	public String getText() throws IOException {
		fileName = getAccountNumber() + ".txt";
		String content = "";
		// get the file stream--read it from disk
		FileInputStream fstream = new FileInputStream(path + fileName);
		// get the data in the file
		DataInputStream fileIn = new DataInputStream(fstream);
		// buffer it
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(
				fileIn));
		String stringln;
		// loop through the buffer to get the content
		while ((stringln = buffReader.readLine()) != null) {
			// concatinate in each line and add the new line
			// character
			content += (stringln + "\n");
		}
		// return the string with all the file content
		return content;
	}

}