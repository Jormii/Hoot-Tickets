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

	/*
	@ManyToOne()
	@JoinColumn(name = "eventID")
	private Event showingEvent;
	*/

	// TODO: Produce error
	/*
	 * @OneToMany(mappedBy = "ticketID.ticketShowingID") private List<Ticket>
	 * showingTickets;
	 */

	public Showing() {

	}

	/*
	 * Getters and setters
	 */

	public Showing(ShowingID showingID, String showingPlace) {
		this.showingID = showingID;
		this.showingPlace = showingPlace;
	}

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
