package dad.hoottickets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dad.hoottickets.database.Event;
import dad.hoottickets.database.Showing;
import dad.hoottickets.database.Ticket;

public class EventCreation {

	private Event event;
	private int showingsAmount;
	private List<Showing> showings;
	private Map<Showing, Integer> ticketsAmount;
	private Map<Showing, List<Ticket>> tickets;

	public EventCreation() {
		this.event = null;
		this.showings = new ArrayList<>();
		this.ticketsAmount = new HashMap<>();
		this.tickets = new HashMap<Showing, List<Ticket>>();
	}

	public void startEventCreation(Event event, int showingsAmount) {
		this.event = event;
		this.showingsAmount = showingsAmount;
	}

	public void addShowingToEvent(Showing showing, int ticketsAmount) {
		showings.add(showing);
		this.ticketsAmount.put(showing, ticketsAmount);
	}

	public void addTicketToEvent(Ticket ticket, Showing showing) {
		if (!tickets.containsKey(showing)) {
			tickets.put(showing, new ArrayList<>());
		}

		tickets.get(showing).add(ticket);
	}

	public void reset() {
		event = null;
		showings.clear();
		tickets.clear();
	}

	/*
	 * Getters and setters
	 */

	public Event getEvent() {
		return event;
	}

	public int getShowingsAmount() {
		return showingsAmount;
	}

	public List<Showing> getShowings() {
		return showings;
	}

	public Map<Showing, Integer> getTicketsAmount() {
		return ticketsAmount;
	}

	public Map<Showing, List<Ticket>> getTickets() {
		return tickets;
	}

}
