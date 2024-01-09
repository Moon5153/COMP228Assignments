/*Author: Najmun Nahar
 * Mid Term Test
 * Course: COMP228-004
 * Date: 22-06-2021
 */
public abstract class Flight {
	
	//declare instance variables
	int flightCode;
	String flightName;
	String flightDestination;
	double ticketPrice;
	String flightDateTime;
	
	public Flight()
	{
		flightCode=0;
	}
	
	//Create ToString Method
	public String toString()
	{
		return "\nFlight Code: "+getFlightCode()+"\nFlight Name: "+getFlightName()+"\nFlight Destination : "+getDestination()+
				"\nFlight Date: "+getFlightDateTime()+"\nTicket Price: "+ticketPrice+"\nClass: "+getFlightClass()+" class";
	}
	
	//getter methods
	public int getFlightCode()
	{
		return flightCode;
	}
	public String getFlightName()
	{
		return flightName;
	}
	public String getDestination()
	{
		return flightDestination;
	}
	public String getFlightDateTime()
	{
		return flightDateTime;
	}
	
	//Setter methods
	public void setFlightCode(int code)
	{
		flightCode=code;
	}
	public void setFlightName(String name)
	{
		flightName=name;
	}
	public void setDestination(String destination)
	{
		flightDestination=destination;
	}
	public void setFlightDateTime(String dateTime)
	{
		flightDateTime=dateTime;
	}
	
	//abstract methods
	public abstract void setTicketPrice(double price);
	public abstract String getFlightClass();
	
	//Calculate Revenue method
	public double calculateRevenue(double price,int seats)
	{
		return price*seats;
	}
	
	

}
