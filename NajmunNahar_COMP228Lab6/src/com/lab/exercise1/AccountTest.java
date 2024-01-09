package com.lab.exercise1;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {

	public static void main(String[] args) {

		// create instance of accounts
		Account acc1 = new Account("Moon", 3000);
		Account acc2 = new Account("Max", 2000);

		// create instance of transactions
		Transaction t1 = new Transaction("withdraw", 800, acc1);
		Transaction t2 = new Transaction("deposit", 500, acc2);
		Transaction t3 = new Transaction("withdraw", 2300, acc1);
		Transaction t4 = new Transaction("deposit", 0, acc2);
		Transaction t5 = new Transaction("deposit", 200, acc1);

		// create array list of transactions
		ArrayList<Transaction> transaction = new ArrayList<Transaction>();
		transaction.add(t1);
		transaction.add(t2);
		transaction.add(t3);
		transaction.add(t4);
		transaction.add(t5);

		// Display all transactions
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (Transaction trans : transaction) {
			executor.execute(trans);
		}
		executor.shutdown();

	}

}
