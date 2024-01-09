package com.lab.exercise1;

public class Transaction implements Runnable {

	// declare variables
	private String type; // transaction type
	private double amount; // transaction amount
	private Account account; // transaction account

	// Constructor
	public Transaction(String transType, double transAmt, Account acc) {
		type = transType;
		amount = transAmt;
		account = acc;
	}

	@Override
	public void run() {
		if (type.equalsIgnoreCase("withdraw")) {
			account.withdraw(amount);
		} else {
			account.deposit(amount);
		}
	}

}
