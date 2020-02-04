package dad.hoottickets;

import java.util.ArrayList;
import java.util.List;

public class Event {

	public final int ID;
	public final int SELLER_ID;
	public final String EVENT_NAME;
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

}
