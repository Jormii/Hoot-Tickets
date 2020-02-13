package dad.hoottickets.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eventID;

	@Column(unique = true, nullable = false)
	private String eventName;

	@Column(nullable = false)
	private String eventSummary;

	@Column(nullable = true)
	private String eventDescription;

	@ManyToOne()
	@JoinColumn(name = "sellerEvents")
	private Seller eventSeller;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Showing> eventShowings = new ArrayList<>();

	public Event() {

	}

	public Event(String eventName, String eventSummary, String eventDescription, Seller eventSeller) {
		this.eventName = eventName;
		this.eventSummary = eventSummary;
		this.eventDescription = eventDescription;
		this.eventSeller = eventSeller;
	}

	/*
	 * Getters and setters
	 */

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventSummary() {
		return eventSummary;
	}

	public void setEventSummary(String eventSummary) {
		this.eventSummary = eventSummary;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Seller getEventSeller() {
		return eventSeller;
	}

	public void setEventSeller(Seller eventSeller) {
		this.eventSeller = eventSeller;
	}

	public List<Showing> getEventShowings() {
		return eventShowings;
	}

	public void setEventShowings(List<Showing> eventShowings) {
		this.eventShowings = eventShowings;
	}

}
