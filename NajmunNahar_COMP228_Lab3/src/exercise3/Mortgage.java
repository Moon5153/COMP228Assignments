/*Author: Najmun Nahar
 * Lab Assignment-3
 * Course: COMP228-004
 * Date: 20-06-2021
 */
package exercise3;

public abstract class Mortgage implements MortgageConstants {
	int mortgageNum;
	String customerName;
	double amount;
	double interestRate;
	int term;
	
	//parameterized constructors
	public Mortgage(int num,String name,double loanAmount,double rate,int termValue)
	{
		mortgageNum=num;
		customerName=name;
		interestRate=rate;
		term=termValue;
		if(loanAmount<=maxMortgageAmount)
		{
			amount=loanAmount;
		}
		else
		{
			amount=maxMortgageAmount;
			term=shortTerm;
			System.out.println("\nMortgage amount exceeds!!!\n");
		}
	}
	
	//Getter Methods
	public String getBankName()
	{
		return bankName;
	}
	public int getMortgageNum()
	{
		return mortgageNum;
	}
	public String getCustomerName()
	{
		return customerName;
	}
	public double getAmount()
	{
		return amount;
	}
	public double getInterestRate()
	{
		return interestRate;
	}
	public String getTerm()
	{
		if(term==shortTerm)
		{
			return "Short Term";
		}
		else if(term==mediumTerm)
		{
			return "Medium Term";
		}
		else if(term==longTerm)
		{
			return "Long Term";
		}
		else
		{
			return "Short Term";
		}
	}
	
	
	//Setter Methods
	public void setMortgageNum(int num)
	{
		mortgageNum=num;
	}
	public void setCustomerName(String name)
	{
		customerName=name;
	}
	public void setAmount(double value)
	{
		amount=value;		
	}
	public void setInterestRate(double rate)
	{
		interestRate=rate;
	}
	public void setTerm(int termValue)
	{
		term = termValue;
	}

	//Method to display all Information
	public void getMortgageInfo()
	{
		System.out.println("Bank Name: "+getBankName());
		System.out.println("Mortgage Number: "+getMortgageNum());
		System.out.println("Customer Name: "+getCustomerName());
		System.out.println("Mortgage Amount: "+getAmount());
		System.out.println("Interest rate: "+getInterestRate()+"%");
		System.out.println("Term: "+getTerm());
		
		
	}
	
}
