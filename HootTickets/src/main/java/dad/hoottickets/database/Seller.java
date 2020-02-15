package dad.hoottickets.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Seller extends User {

	@OneToMany(mappedBy = "eventSeller")
	private List<Event> sellerEvents = new ArrayList<>();

	public Seller() {
		super();
	}

	public Seller(String sellerUsername, String sellerEmail, String sellerName, String sellerSurname,
			String sellerPassword) {
		super(sellerUsername, sellerEmail, sellerName, sellerSurname, sellerPassword);
	}

	/*
	 * Getters and setters
	 */

	public List<Event> getSellerEvents() {
		return sellerEvents;
	}

	public void setSellerEvents(List<Event> sellerEvents) {
		this.sellerEvents = sellerEvents;
	}

}
