
public class Business extends Flight{
	//Declare instance variable
	int numOfSeats;
	
	//Null Constructor
	public Business()
	{
		super();
	}
	//Method to set Number of Seats
	public void setNumOfSeats(int num)
	{
		numOfSeats=num;
	}
	@Override
	public void setTicketPrice(double price)
	{
		super.ticketPrice=price;
	}
	@Override
	public String getFlightClass()
	{
		return "Business";
	}
	@Override
	public String toString()
	{
		return "Welcome to Business Class!\n"+"\nFlight Code: "+getFlightCode()+"\nFlight Name: "+getFlightName()+"\nFlight Destination : "+getDestination()+
				"\nFlight Date: "+getFlightDateTime()+"\nTicket Price: "+ticketPrice+"\nClass: "+getFlightClass()+" class"
				+"\nNumber of Seats: "+numOfSeats+"\n"+getFlightClass()+" Class Revenue: $"+calculateRevenue(ticketPrice,numOfSeats)+"\n";
	}
}
