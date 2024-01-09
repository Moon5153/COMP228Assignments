package exercise1;
import java.util.Scanner;


public class AccountTest {

	public static void main(String[] args) {
		//Create an array of objects for Account Class
		Account acArray[] = new Account[2];
		
		//Instantiate object for Chequing class
		Chequing chequing = new Chequing();
		
		//Instantiate object for Savings class
		Savings savings=new Savings();
		
		//Instantiate object for java defined class Scanner
		Scanner input = new Scanner(System.in);
		
		int i;
		
		for(i=0;i<2;i++)
		{
			
			//Take user input for account type		
			System.out.println("Enter account type\nEx:\'Chequing\' or \'Savings\': ");
			String accType= input.next();
			
			//Take user input for balance
			System.out.println("Enter amount");
			double balance= input.nextDouble();
			
			//if user input is chequing then following 
			if(accType.toLowerCase().equals("chequing"))
			{
				acArray[i] = chequing;
				chequing.setAccountNum(1001);
				
			}
			//if user input is savings then following
			else if(accType.toLowerCase().equals("savings"))
			{
				acArray[i] = savings;
				savings.setAccountNum(1002);
			}
			else
			{
				System.out.println("Invalid Account Type");
			}
			
			//set account balance amount
			acArray[i].setBalancedAmount(balance);		
			//display results
			acArray[i].displayInfo();	
			
		}
		
		System.out.println("\nPolymorphic screen for displaying result");
		for(i=0;i<acArray.length;i++)
		{
			acArray[i].displayInfo();	
		}
		
		
		
		
		
		
	}

}
