package dad.hoottickets.eventcreation;

public class ProvisionalTicket {

	private String ticketName;
	private int ticketAmount;
	private int ticketPrice;

	public ProvisionalTicket(String ticketName, int ticketAmount, int ticketPrice) {
		this.ticketName = ticketName;
		this.ticketAmount = ticketAmount;
		this.ticketPrice = ticketPrice;
	}

	/*
	 * Getters and setters
	 */

	public String getTicketName() {
		return ticketName;
	}

	public int getTicketAmount() {
		return ticketAmount;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

}
