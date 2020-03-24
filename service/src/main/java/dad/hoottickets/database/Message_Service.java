package dad.hoottickets.database;

import java.io.Serializable;

public class Message_Service implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2926475728875258030L;

	String email;
	String ticket_id;
	int quantity;
	String showing;
	String event;

	public Message_Service(String email, String ticket_id, int quantity, String showing, String event) {
		this.email = email;
		this.ticket_id = ticket_id;
		this.quantity = quantity;
		this.showing = showing;
		this.event = event;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getShowing() {
		return showing;
	}

	public void setShowing(String showing) {
		this.showing = showing;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}
