import javax.swing.JOptionPane;
public class TestFlight {

	public static void main(String[] args) {
		
		//Instantiate Business class object
		Business business=new Business();
		//Instantiate Economy class object
		Economy economy = new Economy();
		
		//Input Number of seats for business class
		String input=JOptionPane.showInputDialog("Enter the Number of Seats for Business class");
		int numOfSeats=Integer.parseInt(input);
		
		//Input Ticket price for business class
		String priceInput=JOptionPane.showInputDialog("Enter the ticket price for Business class");
		double ticketPrice=Double.parseDouble(priceInput);
		
		business.setFlightCode(2431);
		business.setFlightName("Air Canada");
		business.setDestination("London UK");
		business.setFlightDateTime("21-Jul-2021 20:30");
		business.setTicketPrice(ticketPrice);		
		business.setNumOfSeats(numOfSeats);
		business.calculateRevenue(ticketPrice, numOfSeats);	
		
		//Input Number of seats for economy class
		String input2=JOptionPane.showInputDialog("Enter the Number of Seats for Economy Class");
		int numOfSeats2=Integer.parseInt(input2);
		
		//Input Ticket price for economy class
		String priceInput2=JOptionPane.showInputDialog("Enter the ticket price for Economy Class");
		double ticketPrice2=Double.parseDouble(priceInput2);
		
		economy.setFlightCode(2431);
		economy.setFlightName("Air Canada");
		economy.setDestination("London UK");
		economy.setFlightDateTime("21-Jul-2021 20:30");
		economy.setTicketPrice(ticketPrice2);		
		economy.setNumOfSeats(numOfSeats2);
		economy.calculateRevenue(ticketPrice2, numOfSeats2);	
		
		//Calculate total revenue
		double total=(economy.calculateRevenue(ticketPrice2, numOfSeats2))+(business.calculateRevenue(ticketPrice, numOfSeats));
		//Show output dialog
		JOptionPane.showMessageDialog(null, business.toString()+"\n"+economy.toString()+"\nTotal Revenue: $"+total);
		
		//Also print on Console
		System.out.println(business.toString());
		System.out.println(economy.toString());
		

	}

}
