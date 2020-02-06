package dad.hoottickets.database;

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

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "showingID.showinDate"), @JoinColumn(name = "showingID.showingEventID") })
	private Showing showing;

	// TODO: Produce error
	/*
	 * @ManyToMany(mappedBy = "userUsername") private List<User> ticketBuyers;
	 */

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

	public Showing getShowing() {
		return showing;
	}

	public void setShowing(Showing showing) {
		this.showing = showing;
	}

}
