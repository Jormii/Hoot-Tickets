package dad.hoottickets;

public class TemplatesAttributes {

	public static class HomePage {

		public static final String TEMPLATE_NAME = "HomePage";

		public static final String USERNAME_ATTR = "username";
		public static final String LOGGED_IN_ATTR = "loggedIn";
		public static final String EVENTS_LIST_ATTR = "eventsList";
	}

	public static class EventPage {

		public static final String TEMPLATE_NAME = "EventPage";

		public static final String EVENT_NAME_ATTR = "eventName";
		public static final String EVENT_SUMMARY_ATTR = "eventSummary";
		public static final String EVENT_DESCRIPTION_ATTR = "eventDescription";
		public static final String EVENT_SHOWINGS_LIST_ATTR = "eventShowingsList";
	}

	public static class TicketSelectionPage {

		public static final String TEMPLATE_NAME = "TicketSelectionPage";

		public static final String EVENT_NAME_ATTR = "eventName";
		public static final String EVENT_SUMMARY_ATTR = "eventSummary";
		public static final String SHOWING_TIME_ATTR = "showingTime";
		public static final String SHOWING_PLACE_ATTR = "showingPlace";
		public static final String SHOWING_TICKETS_ATTR = "showingTicketsList";
	}

	public static class CheckoutPage {

		public static final String TEMPLATE_NAME = "CheckoutPage";

		public static final String EVENT_NAME_ATTR = "eventName";
		public static final String SHOWING_TIME_ATTR = "showingTime";
		public static final String SHOWING_PLACE_ATTR = "showingPlace";
		public static final String TICKETS_SELECTED_ATTR = "ticketsSelected";
	}

	public static class FinishedCheckoutPage {

		public static final String TEMPLATE_NAME = "FinishedCheckoutPage";
	}

	public static class EventCreationPage {

		public static final String TEMPLATE_NAME = "EventCreationPage";
	}

	public static class EventCreationShowingsPage {

		public static final String TEMPLATE_NAME = "EventCreationShowingsPage";

		public static final String EVENT_NAME_ATTR = "eventName";
		public static final String EVENT_SUMMARY_ATTR = "eventSummary";
		public static final String EVENT_DESCRIPTION_ATTR = "eventDescription";
		public static final String PROVISIONAL_SHOWINGS_ATTR = "provisionalShowings";
	}

	public static class ShowingCreationPage {

		public static final String TEMPLATE_NAME = "ShowingCreationPage";
	}

	public static class TicketCreationPage {

		public static final String TEMPLATE_NAME = "TicketCreationPage";

		public static final String SHOWING_DATE_ATTR = "showingDate";
		public static final String SHOWING_PLACE_ATTR = "showingPlace";
		public static final String SHOWING_INDEX_ATTR = "showingIndex";
	}

	public static class EventCreatedPage {

		public static final String TEMPLATE_NAME = "EventCreatedPage";
	}

	public static class RegistrationPage {

		public static final String TEMPLATE_NAME = "RegistrationPage";

		public static final String ERROR_MESSAGE_ATTR = "errorMessage";
	}
	
	/*
	 * Ticket refund
	 */
	public static class UserTicketsPage {
		
		public static final String TEMPLATE_NAME = "UserTicketsPage";
		
		public static final String TICKETS_BOUGHT_ATTR = "ticketsBought";
	}

	public static class LoginPage{
		
		public static final String TEMPLATE_NAME =  "LoginPage";
		
		public static final String ERROR_MESSAGE_ATTR =  "errorMessage";
		
	}
}
