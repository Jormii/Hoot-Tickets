package dad.hoottickets;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import dad.hoottickets.database.Seller;
import dad.hoottickets.database.User;
import dad.hoottickets.eventcreation.EventCreation;

@Component
@SessionScope
public class ClientSession {

	private User user;
	private boolean loggedIn;
	private boolean isSeller;
	private EventCreation eventCreation = new EventCreation();

	public void resetSession() {
		user = null;
		loggedIn = false;
		isSeller = false;
		eventCreation.reset();
	}

	public void logIn(User user) throws Exception {
		if (loggedIn) {
			throw new Exception("Session already has a user logged in");
		}

		this.user = user;
		this.isSeller = user instanceof Seller;
		this.loggedIn = true;
	}

	public void logOut() throws Exception {
		if (!loggedIn) {
			throw new Exception("No user logged in");
		}

		user = null;
		loggedIn = false;
	}

	/*
	 * Getters and setters
	 */

	public User getUser() {
		return user;
	}

	public boolean hasLoggedIn() {
		return loggedIn;
	}

	public boolean isSeller() {
		return isSeller;
	}

	public EventCreation getEventCreation() {
		return eventCreation;
	}

}
