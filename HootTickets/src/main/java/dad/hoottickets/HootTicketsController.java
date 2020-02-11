package dad.hoottickets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.hoottickets.database.Event;
import dad.hoottickets.database.EventRepository;
import dad.hoottickets.database.Seller;
import dad.hoottickets.database.Showing;
import dad.hoottickets.database.ShowingID;
import dad.hoottickets.database.ShowingRepository;
import dad.hoottickets.database.Ticket;
import dad.hoottickets.database.TicketID;
import dad.hoottickets.database.TicketPurchase;
import dad.hoottickets.database.TicketPurchaseID;
import dad.hoottickets.database.TicketPurchaseRepository;
import dad.hoottickets.database.TicketRepository;
import dad.hoottickets.database.User;
import dad.hoottickets.database.UserRepository;

@Controller
public class HootTicketsController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ShowingRepository showingRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TicketPurchaseRepository ticketPurchaseRepository;

	@PostConstruct
	public void init() {
		String userUsername = "User";
		String userEmail = "Correo del usuario";
		String userName = "Nombre del usuario";
		String userSurname = "Apellido del usuario";
		String userPassword = "Contraseña del usuario";
		User user = new User(userUsername, userEmail, userName, userSurname, userPassword);

		userRepository.save(user);

		String sellerUsername = "Seller";
		String sellerEmail = "Correo del vendedor";
		String sellerName = "Nombre del vendedor";
		String sellerSurname = "Apellido del vendedor";
		String sellerPassword = "Contraseña del vendedor";
		Seller seller = new Seller(sellerUsername, sellerEmail, sellerName, sellerSurname, sellerPassword);

		userRepository.save(seller);

		String eventName = "Nombre evento";
		String eventSummary = "Resumen evento";
		String eventDescription = "Descripción evento";
		Event event = new Event(eventName, eventSummary, eventDescription, seller);

		eventRepository.save(event);

		ShowingID showingID = new ShowingID(new Date(), event);
		String showingPlace = "Lugar de la sesion";
		Showing showing = new Showing(showingID, showingPlace);

		showingRepository.save(showing);

		String ticketName = "Nombre de la entrada";
		TicketID ticketID = new TicketID(ticketName, showing);
		int ticketPrice = 10;
		int ticketTotalSeats = 100;
		Ticket ticket = new Ticket(ticketID, ticketPrice, ticketTotalSeats);

		ticketRepository.save(ticket);

		TicketPurchaseID ticketPurchaseID = new TicketPurchaseID(user, ticket);
		int quantity = 1;
		TicketPurchase ticketPurchase = new TicketPurchase(ticketPurchaseID, quantity);

		ticketPurchaseRepository.save(ticketPurchase);
	}

	@RequestMapping("/testHomePage")
	public String home(Model model) {
		List<Event> eventsList = eventRepository.findAll();

		model.addAttribute(TemplatesAttributes.HomePage.EVENTS_LIST_ATTR, eventsList);

		return TemplatesAttributes.HomePage.TEMPLATE_NAME;
	}

	@RequestMapping("/testEventPage/{eventID}")
	private String eventPageTest(Model model, @PathVariable String eventID) {
		int eventIDAsInt = Integer.parseInt(eventID);

		Event event = eventRepository.findById(eventIDAsInt).get();
		String eventName = event.getEventName();
		String eventSummary = event.getEventSummary();
		String eventDescription = event.getEventDescription();
		List<Showing> eventShowings = event.getEventShowings();

		model.addAttribute(TemplatesAttributes.EventPage.EVENT_NAME_ATTR, eventName);
		model.addAttribute(TemplatesAttributes.EventPage.EVENT_SUMMARY_ATTR, eventSummary);
		model.addAttribute(TemplatesAttributes.EventPage.EVENT_DESCRIPTION_ATTR, eventDescription);
		model.addAttribute(TemplatesAttributes.EventPage.EVENT_SHOWINGS_LIST_ATTR, eventShowings);

		return TemplatesAttributes.EventPage.TEMPLATE_NAME;
	}

	@RequestMapping("/testTicketSelectionPage")
	private String ticketSelectionPage(Model model, @RequestParam String eventID, @RequestParam String showingDate) {
		Showing showing = showingRepository.findAll().get(0);
		String eventName = showing.getShowingID().getShowingEvent().getEventName();
		String eventSummary = showing.getShowingID().getShowingEvent().getEventSummary();
		Date showingTime = showing.getShowingID().getShowingDate();
		String showingPlace = showing.getShowingPlace();
		List<Ticket> showingTickets = showing.getShowingTickets();

		model.addAttribute(TemplatesAttributes.TicketSelectionPage.EVENT_NAME_ATTR, eventName);
		model.addAttribute(TemplatesAttributes.TicketSelectionPage.EVENT_SUMMARY_ATTR, eventSummary);
		model.addAttribute(TemplatesAttributes.TicketSelectionPage.SHOWING_TIME_ATTR, showingTime);
		model.addAttribute(TemplatesAttributes.TicketSelectionPage.SHOWING_PLACE_ATTR, showingPlace);
		model.addAttribute(TemplatesAttributes.TicketSelectionPage.SHOWING_TICKETS_ATTR, showingTickets);

		return TemplatesAttributes.TicketSelectionPage.TEMPLATE_NAME;
	}

	@RequestMapping("/testCheckoutPage")
	private String checkoutPage(Model model) {
		// TODO: La información de cuántas entradas se han elegido se tienen que recibir
		// por HTTP

		Ticket ticket = ticketRepository.findAll().get(0);
		String eventName = ticket.getTicketID().getTicketShowing().getShowingID().getShowingEvent().getEventName();
		Date showingTime = ticket.getTicketID().getTicketShowing().getShowingID().getShowingDate();
		String showingPlace = ticket.getTicketID().getTicketShowing().getShowingPlace();

		model.addAttribute(TemplatesAttributes.CheckoutPage.EVENT_NAME_ATTR, eventName);
		model.addAttribute(TemplatesAttributes.CheckoutPage.SHOWING_TIME_ATTR, showingTime);
		model.addAttribute(TemplatesAttributes.CheckoutPage.SHOWING_PLACE_ATTR, showingPlace);
		model.addAttribute(TemplatesAttributes.CheckoutPage.TICKETS_SELECTED_ATTR, ticket);

		return TemplatesAttributes.CheckoutPage.TEMPLATE_NAME;
	}

	@RequestMapping("/testFinishedCheckoutPage")
	private String finishedCheckoutPage(Model model) {
		return TemplatesAttributes.FinishedCheckoutPage.TEMPLATE_NAME;
	}

	@RequestMapping("/testEventCreation")
	private String createEvent(Model model) {
		return TemplatesAttributes.EventCreationPage.TEMPLATE_NAME;
	}

	@PostMapping("/testShowingCreation")
	private String createShowing(Model model, @RequestParam String eventName, @RequestParam String eventSummary,
			@RequestParam String eventDescription, @RequestParam int showingsAmount) {

		String templateToReturn = TemplatesAttributes.ShowingCreationPage.TEMPLATE_NAME;
		Event alreadyExistingEvent = eventRepository.findByEventName(eventName);
		if (alreadyExistingEvent == null) {
			// TODO: Obtener el vendedor de la sesion
			Event newEvent = new Event(eventName, eventSummary, eventDescription,
					(Seller) userRepository.findById("Seller").get());

			// TODO: Incluir esto en la sesion. No guardarlo directamente en la BBDD
			eventRepository.save(newEvent);

			// TODO: Crear tantas sesiones como se ha indicado
			model.addAttribute(TemplatesAttributes.ShowingCreationPage.EVENT_NAME_ATTR, eventName);
			model.addAttribute(TemplatesAttributes.ShowingCreationPage.SHOWINGS_AMOUNT_ATTR, showingsAmount);
		} else {
			String errorMessage = String.format("An event with this name (%s) already exists", eventName);
			model.addAttribute(TemplatesAttributes.EventCreationPage.ERROR_MESSAGE_ATTR, errorMessage);
			templateToReturn = TemplatesAttributes.EventCreationPage.TEMPLATE_NAME;
		}

		return templateToReturn;
	}

	@PostMapping("/testTicketCreation")
	private String createTicket(Model model, @RequestParam String showingPlace, @RequestParam String showingDate,
			@RequestParam int showingTicketsAmount) {
		model.addAttribute(TemplatesAttributes.TicketCreationPage.SHOWING_PLACE_ATTR, showingPlace);
		model.addAttribute(TemplatesAttributes.TicketCreationPage.SHOWING_DATE_ATTR, showingDate);
		model.addAttribute(TemplatesAttributes.TicketCreationPage.EVENT_NAME_ATTR, "<NOMBRE EVENTO>");
		System.out.println(showingTicketsAmount); // TODO

		return TemplatesAttributes.TicketCreationPage.TEMPLATE_NAME;
	}

	@PostMapping("/testCompleteCreation")
	private String completeEventCreation(Model model, @RequestParam String ticketName, @RequestParam int ticketPrice,
			@RequestParam int ticketTotal) {
		
		// TODO
		System.out.println(ticketName);
		System.out.println(ticketPrice);
		System.out.println(ticketTotal);
		
		return TemplatesAttributes.EventCreatedPage.TEMPLATE_NAME;
	}

}
