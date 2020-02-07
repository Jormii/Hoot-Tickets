package dad.hoottickets.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TicketPurchase {

	@Id
	private TicketPurchaseID ticketPurchaseID;

	@Column(nullable = false)
	private int quantity;

	public TicketPurchase() {

	}

	public TicketPurchase(TicketPurchaseID ticketPurchaseID, int quantity) {
		this.ticketPurchaseID = ticketPurchaseID;
		this.quantity = quantity;
	}

	/*
	 * Getters and setters
	 */

	public TicketPurchaseID getTicketPurchaseID() {
		return ticketPurchaseID;
	}

	public void setTicketPurchaseID(TicketPurchaseID ticketPurchaseID) {
		this.ticketPurchaseID = ticketPurchaseID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
