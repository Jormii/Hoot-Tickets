package dad.hoottickets;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DummyController {

	@RequestMapping("/dummyEventPage")
	private String eventPageTest(Model model) {
		String eventName = "Nombre del evento";
		String eventSummary = "Resumen del evento";
		String eventDescription = "Descripción del evento";
		/*
		 * List<TicketOld> eventDaysList = Arrays.asList(new TicketOld(0, new
		 * TimeAndLocation(new Date(), "Lugar 1")), new TicketOld(1, new
		 * TimeAndLocation(new Date(), "Lugar 2")), new TicketOld(2, new
		 * TimeAndLocation(new Date(), "Lugar 3")));
		 */

		model.addAttribute(TemplatesAttributes.EventPage.EVENT_NAME_ATTR, eventName);
		model.addAttribute(TemplatesAttributes.EventPage.EVENT_SUMMARY_ATTR, eventSummary);
		model.addAttribute(TemplatesAttributes.EventPage.EVENT_DESCRIPTION_ATTR, eventDescription);
		// model.addAttribute(TemplatesAttributes.EventPage.EVENT_DAYS_LIST_ATTR,
		// eventDaysList);

		return TemplatesAttributes.EventPage.TEMPLATE_NAME;
	}

	@RequestMapping("/dummyTicketSelectionPage")
	private String ticketSelectionPage(Model model) {
		String eventName = "Nombre del evento";
		String eventSummary = "Resumen del evento";
		/*
		 * TimeAndLocation timeAndLocation = new TimeAndLocation(new Date(),
		 * "Lugar en el que se celebra"); List<Seat> seats = Arrays.asList(new Seat(50,
		 * 20, 10), new Seat(20, 20, 100), new Seat(5, 5, 200));
		 */

		model.addAttribute(TemplatesAttributes.TicketSelectionPage.EVENT_NAME_ATTR, eventName);
		model.addAttribute(TemplatesAttributes.TicketSelectionPage.EVENT_SUMMARY_ATTR, eventSummary);
		// model.addAttribute(TemplatesAttributes.TicketSelectionPage.EVENT_TIME_AND_LOCATION_ATTR,
		// timeAndLocation);
		// model.addAttribute(TemplatesAttributes.TicketSelectionPage.SEATS_LIST_ATTR,
		// seats);

		return TemplatesAttributes.TicketSelectionPage.TEMPLATE_NAME;
	}

	@RequestMapping("/dummyCheckoutPage")
	private String checkoutPage(Model model) {
		String eventName = "Nombre del evento";
		/*
		 * TimeAndLocation eventTimeAndLocation = new TimeAndLocation(new Date(),
		 * "Lugar"); List<Seat> seatsSelected = Arrays.asList(new Seat(10, 10, 10), new
		 * Seat(20, 20, 20));
		 */

		model.addAttribute(TemplatesAttributes.CheckoutPage.EVENT_NAME_ATTR, eventName);
		// model.addAttribute(TemplatesAttributes.CheckoutPage.EVENT_TIME_AND_LOCATION_ATTR,
		// eventTimeAndLocation);
		// model.addAttribute(TemplatesAttributes.CheckoutPage.SEATS_SELECTED_ATTR,
		// seatsSelected);

		return TemplatesAttributes.CheckoutPage.TEMPLATE_NAME;
	}

	@RequestMapping("/dummyFinishedCheckoutPage")
	private String finishedCheckoutPage(Model model) {
		return TemplatesAttributes.FinishedCheckoutPage.TEMPLATE_NAME;
	}

}
