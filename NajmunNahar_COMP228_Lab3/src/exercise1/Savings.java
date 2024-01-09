package exercise1;

public class Savings extends Account {
	
	//define a setter method for account number in subclass
	public void setAccountNum(int num)
	{
		account_num=num;
	}
	
	//implement abstract methods
	public void setBalancedAmount(double value)
	{
		balanceAmount=value;
	}
	public void displayInfo()
	{
		System.out.println("\nAccount Type: Savings");
		System.out.println("Account Number: "+getAccountNum());
		System.out.println("Account Balance: "+getBalanceAmount()+"\n");
	}
}
