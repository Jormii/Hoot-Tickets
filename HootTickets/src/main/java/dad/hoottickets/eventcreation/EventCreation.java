package dad.hoottickets.eventcreation;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EventCreation implements Serializable {

	private static final long serialVersionUID = 3195744999674062755L;
	
	private ProvisionalEvent provisionalEvent;

	public void startEventCreation(ProvisionalEvent event) {
		provisionalEvent = event;
	}

	public void addShowingToEvent(ProvisionalShowing showing) {
		provisionalEvent.addProvisionalShowing(showing);
	}

	public void addTicketToShowing(int showingIndex, ProvisionalTicket ticket) {
		ProvisionalShowing showing = provisionalEvent.getProvisionalShowings().get(showingIndex);
		showing.addTicket(ticket);
	}

	public boolean isValid() {
		// TODO
		return true;
	}

	public void reset() {
		provisionalEvent = null;
	}

	/*
	 * Getters and setters
	 */

	public ProvisionalEvent getProvisionalEvent() {
		return provisionalEvent;
	}

	public List<ProvisionalShowing> getProvisionalShowings() {
		return provisionalEvent.getProvisionalShowings();
	}

}
