package dad.hoottickets.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Showing {

	@EmbeddedId
	private ShowingID showingID;

	@Column(nullable = false)
	private String showingPlace;

	@OneToMany(mappedBy = "ticketID.ticketShowing")
	private List<Ticket> showingTickets = new ArrayList<>();

	public Showing() {

	}

	public Showing(ShowingID showingID, String showingPlace) {
		this.showingID = showingID;
		this.showingPlace = showingPlace;
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

	public List<Ticket> getShowingTickets() {
		return showingTickets;
	}

	public void setShowingTickets(List<Ticket> showingTickets) {
		this.showingTickets = showingTickets;
	}

}
