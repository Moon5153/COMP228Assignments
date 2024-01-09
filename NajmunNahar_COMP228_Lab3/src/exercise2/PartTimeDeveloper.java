package exercise2;

public class PartTimeDeveloper extends MobileDeveloper {

	//parameterized constructor
	public PartTimeDeveloper(String name,boolean status)
	{
		super(name,status);
	}
	//implementing abstract method
	public int salary(int hour)
	{
		return hour*30;
	}
}
