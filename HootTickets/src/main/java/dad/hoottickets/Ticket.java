package dad.hoottickets;

import java.util.HashMap;
import java.util.Map;

public class Ticket {

	public final int EVENT_ID;
	private TimeAndLocation timeAndLocation;
	private Map<String, Seat> seats;

	public Ticket(int eventId, TimeAndLocation timeAndLocation) {
		this.EVENT_ID = eventId;
		this.timeAndLocation = timeAndLocation;
		this.seats = new HashMap<>();
	}

	public boolean addSeat(String ticketName, Seat seat) {
		if (seats.containsKey(ticketName)) {
			return false;
		}

		seats.put(ticketName, seat);
		return true;
	}

	public boolean removeTicketPrice(String ticketName) {
		if (!seats.containsKey(ticketName)) {
			return false;
		}

		seats.remove(ticketName);
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + EVENT_ID;
		result = prime * result + ((timeAndLocation == null) ? 0 : timeAndLocation.hashCode());
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
		Ticket other = (Ticket) obj;
		if (EVENT_ID != other.EVENT_ID)
			return false;
		if (timeAndLocation == null) {
			if (other.timeAndLocation != null)
				return false;
		} else if (!timeAndLocation.equals(other.timeAndLocation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Event with ID %d, happening %s. Seats in sale:\n%s", EVENT_ID, timeAndLocation, seats);
	}

}
