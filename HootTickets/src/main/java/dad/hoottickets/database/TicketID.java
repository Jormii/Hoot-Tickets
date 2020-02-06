package dad.hoottickets.database;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Embeddable
public class TicketID implements Serializable {

	private String ticketName;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "showingID.showinDate"),
		@JoinColumn(name = "showingID.showingEventID")
	})
	private Showing ticketShowing;

	public TicketID() {

	}

	public TicketID(String ticketName, Showing ticketShowing) {
		this.ticketName = ticketName;
		this.ticketShowing = ticketShowing;
	}

	/*
	 * hashCode() and equals()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticketName == null) ? 0 : ticketName.hashCode());
		result = prime * result + ((ticketShowing == null) ? 0 : ticketShowing.hashCode());
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
		TicketID other = (TicketID) obj;
		if (ticketName == null) {
			if (other.ticketName != null)
				return false;
		} else if (!ticketName.equals(other.ticketName))
			return false;
		if (ticketShowing == null) {
			if (other.ticketShowing != null)
				return false;
		} else if (!ticketShowing.equals(other.ticketShowing))
			return false;
		return true;
	}

	/*
	 * Getters and setters
	 */

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public Showing getTicketShowing() {
		return ticketShowing;
	}

	public void setTicketShowing(Showing ticketShowing) {
		this.ticketShowing = ticketShowing;
	}

}
