package exercise3;
import java.util.Scanner;
public class ProcessMortgage {

	public static void main(String[] args) {
		
		//Create an Array of objects
		Mortgage mortgage[]=new Mortgage[3];
		Scanner scan = new Scanner(System.in);
				
		for(int i=0;i<2;i++)
		{
			if(i==1)
			{
				System.out.println("Enter Details for Second Customer\n");
			}
			//User Input for Mortgage Type
			System.out.println("Enter Mortgage type\nExample: \'Business\' or \'Personal\':\n ");
			String type=scan.nextLine();
			
			//Check if user input is Business or Personal
			if(type.equalsIgnoreCase("business"))
			{
				//user input mortgage details
				System.out.println("Enter Customer Name: ");
				String name = scan.nextLine();
				
				System.out.println("Enter Mortgage number: ");
				int num = scan.nextInt();
				
				System.out.println("Enter amount of Mortgage: ");
				double amount = scan.nextDouble();
				
				System.out.println("Enter current interest rate: ");
				double rate = scan.nextDouble();
				
				System.out.println("Enter term in years: ");
				int term = scan.nextInt();
				scan.nextLine();
				
				mortgage[i]=new BusinessMortgage(num,name,amount,rate,term);
			}
			else if(type.equalsIgnoreCase("personal"))
			{
				System.out.println("Enter Customer Name: ");
				String name = scan.nextLine();
				
				System.out.println("Enter Mortgage number: ");
				int num = scan.nextInt();
				
				System.out.println("Enter amount of Mortgage: ");
				double amount = scan.nextDouble();
				
				System.out.println("Enter current interest rate: ");
				double rate = scan.nextDouble();
				
				System.out.println("Enter term in years: ");
				int term = scan.nextInt();
				scan.nextLine();
				mortgage[i]=new PersonalMortgage(num,name,amount,rate,term);
			}
			else
			{
				System.out.println("Invalid Input");
				break;
			}
			
		}
		
		//Display values using getMortgageInfo method
		for(int i=0;i<2;i++)
		{
			System.out.println("\nMortgage Details of Customer number "+(i+1)+": ");
			mortgage[i].getMortgageInfo();
		}
	
		
	}

}
