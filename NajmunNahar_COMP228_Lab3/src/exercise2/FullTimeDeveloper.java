package exercise2;

public class FullTimeDeveloper extends MobileDeveloper {

	//parameterized constructor
	public FullTimeDeveloper(String name,boolean status)
	{
		super(name,status);
	}
	
	//implementing abstract method
	public int salary(int hour)
	{
		return 6000;
	}
}
