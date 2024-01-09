package exercise3;

public class BusinessMortgage extends Mortgage {
	
	//Parameterized constructor
	BusinessMortgage(int num,String name,double loanAmount,double rate,int term)
	{
		super(num,name,loanAmount,rate+2,term);
	
	}

}
