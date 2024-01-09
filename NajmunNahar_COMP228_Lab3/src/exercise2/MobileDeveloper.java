/*Author: Najmun Nahar
 * Lab Assignment-3
 * Course: COMP228-004
 * Date: 20-06-2021
 */
package exercise2;

public abstract class MobileDeveloper {

	//declaring variables
	String developerName;
	boolean fullTimeStatus;
	
	//parameterized constructor
	public MobileDeveloper(String name, boolean status)
	{
		developerName=name;
		fullTimeStatus=status;
	}
	
	//abstract method
	public abstract int salary(int hours);
}
