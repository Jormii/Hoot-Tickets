package dad.hoottickets.database;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Embeddable
public class TicketPurchaseID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7823205489460262472L;

	@ManyToOne()
	@JoinColumn(name = "userUsername")
	private User user;

	@ManyToOne()
	@JoinColumns({ @JoinColumn(name = "ticketID.ticketName"), @JoinColumn(name = "ticketID.showingID.showingDate"),
			@JoinColumn(name = "ticketID.showingID.showingEvenID") })
	private Ticket ticket;

	public TicketPurchaseID() {

	}

	public TicketPurchaseID(User user, Ticket ticket) {
		this.user = user;
		this.ticket = ticket;
	}

	/*
	 * hashCode() and equals()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticket == null) ? 0 : ticket.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketPurchaseID other = (TicketPurchaseID) obj;
		if (ticket == null) {
			if (other.ticket != null)
				return false;
		} else if (!ticket.equals(other.ticket))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	/*
	 * Getters and setters
	 */

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
