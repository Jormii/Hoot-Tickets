package dad.hoottickets.database;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ShowingID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9015996705336309459L;

	private Date showingDate;


	private String showingEvent;

	public ShowingID() {

	}

	public ShowingID(Date showingDate,String showingEvent) {
		this.showingDate = showingDate;
		this.showingEvent = showingEvent;
	}

	/*
	 * hashCode() and equals()
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((showingDate == null) ? 0 : showingDate.hashCode());
		result = prime * result + ((showingEvent == null) ? 0 : showingEvent.hashCode());
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
		if (showingEvent == null) {
			if (other.showingEvent != null)
				return false;
		} else if (!showingEvent.equals(other.showingEvent))
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

	public String getShowingEvent() {
		return showingEvent;
	}

	public void setShowingEvent(String showingEvent) {
		this.showingEvent = showingEvent;
	}

}
