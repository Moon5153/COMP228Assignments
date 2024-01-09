
public class Economy extends Flight {
	//Declare variable
	int numOfSeats;
	
	//Null Constructor
	public Economy()
	{
		super();
	}
	//Method to set Number of seats
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
		return "Economy";
	}
	@Override
	public String toString()
	{
		return "\nWelcome to Economy Class!\n"+"\nFlight Code: "+getFlightCode()+"\nFlight Name: "+getFlightName()+"\nFlight Destination : "+getDestination()+
				"\nFlight Date: "+getFlightDateTime()+"\nTicket Price: "+ticketPrice+"\nNumber of Seats: "+numOfSeats+
				"\nClass: "+getFlightClass()+" class"+"\n"+getFlightClass()+" Class Revenue: $"+calculateRevenue(ticketPrice,numOfSeats)+"\n";
	}
}
