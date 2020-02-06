package dad.hoottickets;

import java.util.ArrayList;
import java.util.List;

public class Event {

	public final int ID;
	public final int SELLER_ID;
	private String EVENT_NAME;
	public final String EVENT_SUMMARY;
	public final String EVENT_DESCRIPTION;
	private List<TimeAndLocation> timeAndLocations;

	public Event(int id, int sellerId, String eventName, String eventSummary, String eventDescription) {
		this.ID = id;
		this.SELLER_ID = sellerId;
		this.EVENT_NAME = eventName;
		this.EVENT_SUMMARY = eventSummary;
		this.EVENT_DESCRIPTION = eventDescription;
		this.timeAndLocations = new ArrayList<>();
	}

	public boolean addTimeAndLocation(TimeAndLocation timeAndLocation) {
		if (timeAndLocations.contains(timeAndLocation)) {
			return false;
		}

		timeAndLocations.add(timeAndLocation);
		return true;
	}

	public boolean removeTimeAndLocation(TimeAndLocation timeAndLocation) {
		if (!timeAndLocations.contains(timeAndLocation)) {
			return false;
		}

		timeAndLocations.remove(timeAndLocation);
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + SELLER_ID;
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
		Event other = (Event) obj;
		if (ID != other.ID)
			return false;
		if (SELLER_ID != other.SELLER_ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String header = String.format("Event with ID %d, organized by seller with ID %d", ID, SELLER_ID);
		String eventDescription = String.format("%s\nSummary: %s\nDescription: %s", EVENT_NAME, EVENT_SUMMARY, EVENT_DESCRIPTION);
		String dates = String.format("Taking place these days: ", timeAndLocations);
		return String.format("%s\n%s\n%s", header, eventDescription, dates);
	}

}
