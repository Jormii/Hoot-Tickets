package dad.hoottickets;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.hoottickets.database.Event;
import dad.hoottickets.database.Showing;
import dad.hoottickets.database.ShowingID;
import dad.hoottickets.database.Ticket;

@Service
public class EventCreation {

	private ProvisionalEvent provisionalEvent;
	private List<ProvisionalShowing> provisionalShowings;

	static class ProvisionalEvent {

		public String eventName;
		public String eventSummary;
		public String eventDescription;

	}

	static class ProvisionalShowing {

		public String showingDate;
		public String showingPlace;
		public List<ProvisionalTicket> provisionalTickets;

		public ProvisionalShowing(String showingDate, String showingPlace) {
			this.showingDate = showingDate;
			this.showingPlace = showingPlace;
			provisionalTickets = new ArrayList<>();
		}

		public void addTicket(String ticketName, int ticketAmount, int ticketPrice) {
			ProvisionalTicket provisionalTicket = new ProvisionalTicket(ticketName, ticketAmount, ticketPrice);
			provisionalTickets.add(provisionalTicket);
		}

	}

	static class ProvisionalTicket {

		public String ticketName;
		public int ticketAmount;
		public int ticketPrice;

		public ProvisionalTicket(String ticketName, int ticketAmount, int ticketPrice) {
			this.ticketName = ticketName;
			this.ticketAmount = ticketAmount;
			this.ticketPrice = ticketPrice;
		}

	}

	public EventCreation() {
		this.provisionalEvent = new ProvisionalEvent();
		this.provisionalShowings = new ArrayList<>();
	}

	public void startEventCreation(String eventName, String eventSummary, String eventDescription) {
		provisionalEvent.eventName = eventName;
		provisionalEvent.eventSummary = eventSummary;
		provisionalEvent.eventDescription = eventDescription;
		provisionalShowings.clear();
	}

	public void addShowingToEvent(String showingDate, String showingPlace) {
		ProvisionalShowing provisionalShowing = new ProvisionalShowing(showingDate, showingPlace);
		provisionalShowings.add(provisionalShowing);
	}

	public boolean isValid() {
		// TODO
		return true;
	}

	public void reset() {
		provisionalEvent = null;
		provisionalShowings.clear();
	}

	/*
	 * Getters and setters
	 */

	public ProvisionalEvent getProvisionalEvent() {
		return provisionalEvent;
	}

	public List<ProvisionalShowing> getProvisionalShowings() {
		return provisionalShowings;
	}

}
