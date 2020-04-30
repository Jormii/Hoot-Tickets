package dad.hoottickets.eventcreation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProvisionalEvent implements Serializable {

	private static final long serialVersionUID = 1544989758727718203L;
	
	private String eventName;
	private String eventSummary;
	private String eventDescription;
	private List<ProvisionalShowing> provisionalShowings;

	public ProvisionalEvent(String eventName, String eventSummary, String eventDescription) {
		this.eventName = eventName;
		this.eventSummary = eventSummary;
		this.eventDescription = eventDescription;
		this.provisionalShowings = new ArrayList<>();
	}

	public void addProvisionalShowing(ProvisionalShowing showing) {
		provisionalShowings.add(showing);
	}

	/*
	 * Getters and setters
	 */

	public String getEventName() {
		return eventName;
	}

	public String getEventSummary() {
		return eventSummary;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public List<ProvisionalShowing> getProvisionalShowings() {
		return provisionalShowings;
	}

}
