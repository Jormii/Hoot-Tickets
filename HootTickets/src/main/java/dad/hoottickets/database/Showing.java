package dad.hoottickets.database;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Showing {

	@EmbeddedId
	private ShowingID showingID;

	@Column(nullable = false)
	private String showingPlace;

	@ManyToOne()
	@JoinColumn(name = "eventShowings")
	private Event showingEvent;

	// TODO: Produce error
	/*
	 * @OneToMany(mappedBy = "ticketID.ticketShowingID") private List<Ticket>
	 * showingTickets;
	 */

	public Showing() {

	}

	public Showing(ShowingID showingID, String showingPlace, Event showingEvent) {
		this.showingID = showingID;
		this.showingPlace = showingPlace;
		this.showingEvent = showingEvent;
	}

	/*
	 * Getters and setters
	 */

	public ShowingID getShowingID() {
		return showingID;
	}

	public void setShowingID(ShowingID showingID) {
		this.showingID = showingID;
	}

	public String getShowingPlace() {
		return showingPlace;
	}

	public void setShowingPlace(String showingPlace) {
		this.showingPlace = showingPlace;
	}

}
