package com.jsteadman.BankingManager;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TransactionLog extends AccountTransactions {

	private String path = "/Users/jonathan/Documents/"; // for windows
														// "C:\\files\\";
	private PrintWriter writer;
	String fileName;

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

	public void startFile(String path) throws IOException {
		// System.out.println(account);
		this.path = path;
		createFile();

	}

	private void createFile() throws IOException {
		fileName = getAccountNumber() + ".txt";
		// create the file, the true means append to
		// the file if it exists, creates it if it doesn't exist
		FileWriter outFile = new FileWriter(path + fileName, true);
		writer = new PrintWriter(outFile);

	}

	public void addText(String ogbalance, String transaction, String balance) {
		// add content to the file
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