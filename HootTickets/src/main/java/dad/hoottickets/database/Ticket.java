package dad.hoottickets.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Ticket {

	@EmbeddedId
	private TicketID ticketID;

	@Column(nullable = false)
	private int ticketPrice;

	@Column(nullable = false)
	private int ticketTotalSeats;

	@Column(nullable = false)
	private int ticketAvailableSeats;

	@OneToMany(mappedBy = "ticketPurchaseID.ticket")
	private List<TicketPurchase> ticketBuyers = new ArrayList<>();

	public Ticket() {

	}

	public Ticket(TicketID ticketID, int ticketPrice, int ticketTotalSeats) {
		this.ticketID = ticketID;
		this.ticketPrice = ticketPrice;
		this.ticketTotalSeats = ticketTotalSeats;
		this.ticketAvailableSeats = ticketTotalSeats;
	}

	/*
	 * Getters and setters
	 */

	public TicketID getTicketID() {
		return ticketID;
	}

	public void setTicketID(TicketID ticketID) {
		this.ticketID = ticketID;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getTicketTotalSeats() {
		return ticketTotalSeats;
	}

	public void setTicketTotalSeats(int ticketTotalSeats) {
		this.ticketTotalSeats = ticketTotalSeats;
	}

	public int getTicketAvailableSeats() {
		return ticketAvailableSeats;
	}

	public void setTicketAvailableSeats(int ticketAvailableSeats) {
		this.ticketAvailableSeats = ticketAvailableSeats;
	}

	public List<TicketPurchase> getTicketBuyers() {
		return ticketBuyers;
	}

	public void setTicketBuyers(List<TicketPurchase> ticketBuyers) {
		this.ticketBuyers = ticketBuyers;
	}

}
