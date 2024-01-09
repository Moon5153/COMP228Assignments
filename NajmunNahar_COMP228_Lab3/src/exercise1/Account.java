/*Author: Najmun Nahar
 * Lab Assignment-3
 * Course: COMP228-004
 * Date: 20-06-2021
 */
package exercise1;

public abstract class Account {
	
	//Declaring variables
	int account_num;
	String accountType;
	double balanceAmount;

	//get methods
	public double getBalanceAmount()
	{
		return balanceAmount;
	}
	public int getAccountNum()
	{
		return account_num;
	}
		
	//abstract methods
	public abstract void setBalancedAmount(double value);
	public abstract void displayInfo();

}
