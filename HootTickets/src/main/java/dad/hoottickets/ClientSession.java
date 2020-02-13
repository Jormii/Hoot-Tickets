package dad.hoottickets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import dad.hoottickets.database.Event;
import dad.hoottickets.database.Showing;
import dad.hoottickets.database.Ticket;
import dad.hoottickets.database.User;
import dad.hoottickets.eventcreation.EventCreation;

@Component
@SessionScope
public class ClientSession {

	public static class EventCreationStruct {

	}

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

	public void logIn(User user, boolean isSeller) throws Exception {
		if (loggedIn) {
			throw new Exception("Session already has a user logged in");
		}

		this.user = user;
		this.isSeller = isSeller;
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
