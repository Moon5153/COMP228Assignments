/*Author: Najmun Nahar
 * Lab Assignment-6
 * Course: COMP228-004
 * Date: 08-08-2021
 */
package com.lab.exercise1;
public class Account {
	private int accId; // account id
	private static int accountCount = 0;// stores account number
	private String accHolder;// name of account holder
	private double accBalance;// current balance of account

	// Constructor
	public Account(String name, double balance) {
		accountCount++;
		accId = accountCount;// assign account number
		accHolder = name;
		accBalance = balance;
		System.out.println("Account Created- Account ID: " + accId + ", Account Holder Name: " + accHolder
				+ ", Current Balance: " + accBalance+"$");
	}

	// withdraw method
	public synchronized void withdraw(double amount) {
		System.out.println(accHolder + " is making an attempt to withdraw " + amount + "$ from account id: " + accId);
		if (amount <= accBalance) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			accBalance = accBalance - amount;// update account balance
			System.out.println(accHolder + " has successfully withdrawn " + amount + "$ from account id " + accId
					+ ". Now current balance is " + accBalance + "$");

		} else {
			System.out.println("Withdraw unsuccessful!! " + accHolder + " doesn't have sufficient fund");
		}
	}

	// deposit method
	public synchronized void deposit(double amount) {
		System.out.println(accHolder + " is making an attempt to deposit " + amount + "$ to account id: " + accId);
		if (amount > 0) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			accBalance = accBalance + amount; // update balance
			System.out.println(accHolder + " has successfully deposited " + amount + "$ to account id " + accId
					+ ". Now current balance is " + accBalance + "$");

		} else {
			System.out.println("Deposit unsuccessful!! " + accHolder + " does not have sufficient fund to deposit");
		}
	}

}
