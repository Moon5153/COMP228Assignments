package exercise3;

public class PersonalMortgage extends Mortgage {
	//Parameterized Constructor
	PersonalMortgage(int num,String name,double loanAmount,double rate,int term)
	{
		super(num,name,loanAmount,rate+3,term);
	
	}
}
