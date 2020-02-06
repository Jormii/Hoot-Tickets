package dad.hoottickets.database;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class ShowingID implements Serializable {

	private Date showingDate;
	private int showingEventID;

	public ShowingID() {

	}

	public ShowingID(Date showingDate, int showingEventID) {
		this.showingDate = showingDate;
		this.showingEventID = showingEventID;
	}

	/*
	 * hashCode() and equals()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((showingDate == null) ? 0 : showingDate.hashCode());
		result = prime * result + showingEventID;
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
		ShowingID other = (ShowingID) obj;
		if (showingDate == null) {
			if (other.showingDate != null)
				return false;
		} else if (!showingDate.equals(other.showingDate))
			return false;
		if (showingEventID != other.showingEventID)
			return false;
		return true;
	}

	/*
	 * Getters and setters
	 */

	public Date getShowingDate() {
		return showingDate;
	}

	public void setShowingDate(Date showingDate) {
		this.showingDate = showingDate;
	}

	public int getShowingEventID() {
		return showingEventID;
	}

	public void setShowingEventID(int showingEventID) {
		this.showingEventID = showingEventID;
	}

}
