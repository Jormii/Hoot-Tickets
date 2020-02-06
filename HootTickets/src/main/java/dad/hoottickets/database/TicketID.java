package dad.hoottickets.database;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class TicketID implements Serializable {

	private String ticketName;
	private ShowingID ticketShowingID;

	public TicketID() {

	}

	public TicketID(String ticketName, ShowingID ticketShowingID) {
		this.ticketName = ticketName;
		this.ticketShowingID = ticketShowingID;
	}

	/*
	 * hashCode() and equals()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticketName == null) ? 0 : ticketName.hashCode());
		result = prime * result + ((ticketShowingID == null) ? 0 : ticketShowingID.hashCode());
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
		if (ticketShowingID == null) {
			if (other.ticketShowingID != null)
				return false;
		} else if (!ticketShowingID.equals(other.ticketShowingID))
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

	public ShowingID getTicketShowingID() {
		return ticketShowingID;
	}

	public void setTicketShowingID(ShowingID ticketShowingID) {
		this.ticketShowingID = ticketShowingID;
	}

}
