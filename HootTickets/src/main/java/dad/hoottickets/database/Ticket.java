package dad.hoottickets.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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

	@ManyToMany(mappedBy = "userTickets")
	private List<User> ticketBuyers = new ArrayList<>();

	public Ticket() {

	}

	public Ticket(TicketID ticketID, int ticketPrice, int ticketTotalSeats, int ticketAvailableSeats) {
		this.ticketID = ticketID;
		this.ticketPrice = ticketPrice;
		this.ticketTotalSeats = ticketTotalSeats;
		this.ticketAvailableSeats = ticketAvailableSeats;
	}

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

}
