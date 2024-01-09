package exercise2;
import java.util.Scanner;
public class DeveloperTest {

	public static void main(String[] args) {
		
		//Instantiate object for Scanner class
		Scanner scan = new Scanner(System.in);	
		
		//User Input for status of the developer
		System.out.println("Enter status of Developer\nHint: Type true for Full time or false for Part time: ");
		boolean type = scan.nextBoolean();
		
		//Instantiate object for subclasses
		FullTimeDeveloper fulltime = new FullTimeDeveloper("Max",type);
		PartTimeDeveloper parttime = new PartTimeDeveloper("Mason",type);
		
		int hours=0;
		
		//Check if Full time status is true or false
		if(type==true)
		{
			//Display name and Salary
			System.out.println("Developer Name: "+fulltime.developerName);
			System.out.println("Developer Salary: $"+fulltime.salary(hours));
		}
		else if(type==false)
		{
			//Take user input for working hours
			System.out.println("Enter working hours: ");
			hours=scan.nextInt();
			
			//Display name and Salary
			System.out.println("Developer Name: "+parttime.developerName);
			System.out.println("Developer Salary: $"+parttime.salary(hours));			
		}


	}

}
