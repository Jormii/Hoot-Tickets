package dad.hoottickets;

public class TemplatesAttributes {

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

}
