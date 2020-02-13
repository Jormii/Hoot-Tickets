package dad.hoottickets.eventcreation;

import java.util.ArrayList;
import java.util.List;

public class ProvisionalShowing {

	private String showingDate;
	private String showingPlace;
	private List<ProvisionalTicket> provisionalTickets;

	public ProvisionalShowing(String showingDate, String showingPlace) {
		this.showingDate = showingDate;
		this.showingPlace = showingPlace;
		provisionalTickets = new ArrayList<>();
	}

	public void addTicket(ProvisionalTicket ticket) {
		provisionalTickets.add(ticket);
	}

	/*
	 * Getters and setters
	 */

	public String getShowingDate() {
		return showingDate;
	}

	public String getShowingPlace() {
		return showingPlace;
	}

	public List<ProvisionalTicket> getProvisionalTickets() {
		return provisionalTickets;
	}

}
