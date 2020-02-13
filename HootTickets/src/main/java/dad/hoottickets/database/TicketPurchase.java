package dad.hoottickets.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketPurchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long purchaseID;
	
	@Column(unique = true)
	private TicketPurchaseUniqueID ticketPurchaseUniqueID;

	@Column(nullable = false)
	private int quantity;

	public TicketPurchase() {

	}

	public TicketPurchase(TicketPurchaseUniqueID ticketPurchaseUniqueID, int quantity) {
		this.ticketPurchaseUniqueID = ticketPurchaseUniqueID;
		this.quantity = quantity;
	}

	/*
	 * Getters and setters
	 */

	public long getPurchaseID() {
		return purchaseID;
	}

	public void setPurchaseID(long purchaseID) {
		this.purchaseID = purchaseID;
	}

	public TicketPurchaseUniqueID getTicketPurchaseUniqueID() {
		return ticketPurchaseUniqueID;
	}

	public void setTicketPurchaseUniqueID(TicketPurchaseUniqueID ticketPurchaseUniqueID) {
		this.ticketPurchaseUniqueID = ticketPurchaseUniqueID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
