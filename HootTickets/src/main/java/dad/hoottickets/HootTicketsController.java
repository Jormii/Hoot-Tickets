package dad.hoottickets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.sun.tools.javac.util.Pair;

import dad.hoottickets.TemplatesAttributes.EventCreatedPage;
import dad.hoottickets.TemplatesAttributes.EventCreationPage;
import dad.hoottickets.TemplatesAttributes.EventCreationShowingsPage;
import dad.hoottickets.TemplatesAttributes.HomePage;
import dad.hoottickets.TemplatesAttributes.RegistrationPage;
import dad.hoottickets.TemplatesAttributes.ShowingCreationPage;
import dad.hoottickets.TemplatesAttributes.TicketCreationPage;
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
import dad.hoottickets.eventcreation.EventCreation;
import dad.hoottickets.eventcreation.ProvisionalEvent;
import dad.hoottickets.eventcreation.ProvisionalShowing;
import dad.hoottickets.eventcreation.ProvisionalTicket;

@Controller
public class HootTicketsController {

	// TODO: BORRAR
	Seller madeUpSeller = new Seller("MadeUpSeller", "madeup@seller.com", "MadeUp", "Seller", "MyMadeUpPassword");

	@Autowired
	private ClientSession session;

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

		userRepository.save(madeUpSeller);
		userRepository.save(seller);

		String eventName = "Nombre evento";
		String eventSummary = "Resumen evento";
		String eventDescription = "Descripción evento";
		Event event = new Event(eventName, eventSummary, eventDescription, seller);

		eventRepository.save(event);

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = LocalDateTime.from(dateFormatter.parse("1998-01-26 10:10"));
		ShowingID showingID = new ShowingID(date, event.getEventName());
		String showingPlace = "Lugar de la sesion";
		Showing showing = new Showing(showingID, showingPlace,event);

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

	private void updateHTTPSession(HttpSession httpSession) {
		if (httpSession.isNew()) {
			session.resetSession();
		}
	}

	@RequestMapping("/testHomePage")
	private String home(Model model) {
		List<Event> eventsList = eventRepository.findAll();

		if (session.hasLoggedIn()) {
			model.addAttribute(HomePage.USERNAME_ATTR, session.getUser().getUserName());
		}
		model.addAttribute(HomePage.LOGGED_IN_ATTR, session.hasLoggedIn());
		model.addAttribute(HomePage.EVENTS_LIST_ATTR, eventsList);

		return TemplatesAttributes.HomePage.TEMPLATE_NAME;
	}

	@PostMapping("/testEventPage")
	private String eventPageTest(Model model, @RequestParam int eventID) {
		//System.out.println(eventID);
		//int eventIDAsInt = Integer.parseInt(eventID);

		Event event = eventRepository.findById(eventID).get();
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

	@PostMapping("/testTicketSelectionPage")
	private String ticketSelectionPage(Model model,@RequestParam String ShowingDate,@RequestParam String ShowingEvent) throws ParseException {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime date = LocalDateTime.from(dateFormatter.parse(ShowingDate));
		Showing showing = showingRepository.findById(new ShowingID(date,ShowingEvent)).get();
		String eventName = showing.getShowingEvent().getEventName();
		String eventSummary = showing.getShowingEvent().getEventSummary();
		String showingPlace = showing.getShowingPlace();
		List<Ticket> showingTickets = showing.getShowingTickets();

		model.addAttribute(TemplatesAttributes.TicketSelectionPage.EVENT_NAME_ATTR, eventName);
		model.addAttribute(TemplatesAttributes.TicketSelectionPage.EVENT_SUMMARY_ATTR, eventSummary);
		model.addAttribute(TemplatesAttributes.TicketSelectionPage.SHOWING_TIME_ATTR, ShowingDate);
		model.addAttribute(TemplatesAttributes.TicketSelectionPage.SHOWING_PLACE_ATTR, showingPlace);
		model.addAttribute(TemplatesAttributes.TicketSelectionPage.SHOWING_TICKETS_ATTR, showingTickets);

		return TemplatesAttributes.TicketSelectionPage.TEMPLATE_NAME;
	}

	@PostMapping("/testCheckoutPage")
	private String checkoutPage(Model model,@RequestParam("seat[]") List<Integer> to,@RequestParam String ShowingDate,@RequestParam String ShowingEvent) throws ParseException {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime date = LocalDateTime.from(dateFormatter.parse(ShowingDate));
		Showing showing = showingRepository.findById(new ShowingID(date,ShowingEvent)).get();
		List<Ticket> showingTickets = showing.getShowingTickets();
		List<Pair<Ticket,Integer>> map= new ArrayList<Pair<Ticket,Integer>>();
		int i=0;
		for(Ticket ticket : showingTickets) {
			Pair<Ticket,Integer> pair=Pair.of(ticket,to.get(i));
			
		map.add(pair);
		i++;
		}
		
		String eventName = showing.getShowingEvent().getEventName();
		String showingPlace =showing.getShowingPlace();
		model.addAttribute(TemplatesAttributes.CheckoutPage.EVENT_NAME_ATTR, eventName);
		model.addAttribute(TemplatesAttributes.CheckoutPage.SHOWING_TIME_ATTR, ShowingDate);
		model.addAttribute(TemplatesAttributes.CheckoutPage.SHOWING_PLACE_ATTR, showingPlace);
		model.addAttribute(TemplatesAttributes.CheckoutPage.TICKETS_SELECTED_ATTR, map);
		return TemplatesAttributes.CheckoutPage.TEMPLATE_NAME;
	}

	@PostMapping("/testFinishedCheckoutPage")
	private String finishedCheckoutPage(Model model,@RequestParam("seat[]") List<Integer> to,@RequestParam String ShowingDate,@RequestParam String ShowingEvent) throws ParseException {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime date = LocalDateTime.from(dateFormatter.parse(ShowingDate));
		Showing showing = showingRepository.findById(new ShowingID(date,ShowingEvent)).get();
		int i=0;
		for(int seat : to) {
			System.out.println(seat);
		}
		List<Ticket> showingTickets = showing.getShowingTickets();
		User user= userRepository.findAll().get(0);
		for(Ticket ticket : showingTickets) {
			if(to.get(i)!= 0) {
				TicketPurchaseID ticketPurchaseID = new TicketPurchaseID(user, ticket);
				TicketPurchase ticketPurchase = new TicketPurchase(ticketPurchaseID,to.get(i));
				ticketPurchaseRepository.save(ticketPurchase);
				ticket.setTicketAvailableSeats(ticket.getTicketAvailableSeats()-to.get(i));
				ticketRepository.save(ticket);
			}
			i++;
		}
		return TemplatesAttributes.FinishedCheckoutPage.TEMPLATE_NAME;
	}

	// TODO: BORRAR
	@RequestMapping("/")
	private String accesoRapido(Model model) {
		return home(model);
	}

	@RequestMapping("/eventCreation")
	private String eventCreation(Model model) {
		return EventCreationPage.TEMPLATE_NAME;
	}

	@PostMapping("/eventCreation/sendEventData")
	private RedirectView receiveEventData(@RequestParam String eventName, @RequestParam String eventSummary,
			@RequestParam String eventDescription) {
		EventCreation eventCreation = session.getEventCreation();
		ProvisionalEvent provisionalEvent = new ProvisionalEvent(eventName, eventSummary, eventDescription);
		eventCreation.startEventCreation(provisionalEvent);

		return new RedirectView("/eventCreation/showings");
	}

	@RequestMapping("eventCreation/showings")
	private String eventCreationShowings(Model model) {
		EventCreation eventCreation = session.getEventCreation();

		ProvisionalEvent provisionalEvent = eventCreation.getProvisionalEvent();
		model.addAttribute(EventCreationShowingsPage.EVENT_NAME_ATTR, provisionalEvent.getEventName());
		model.addAttribute(EventCreationShowingsPage.EVENT_SUMMARY_ATTR, provisionalEvent.getEventSummary());
		model.addAttribute(EventCreationShowingsPage.EVENT_DESCRIPTION_ATTR, provisionalEvent.getEventDescription());
		model.addAttribute(EventCreationShowingsPage.PROVISIONAL_SHOWINGS_ATTR, eventCreation.getProvisionalShowings());

		return EventCreationShowingsPage.TEMPLATE_NAME;
	}

	@RequestMapping("eventCreation/createShowing")
	private String showingCreation(Model model) {
		return ShowingCreationPage.TEMPLATE_NAME;
	}

	@PostMapping("/eventCreation/sendShowingData")
	private RedirectView receiveShowingData(@RequestParam String showingDate, @RequestParam String showingTime,
			@RequestParam String showingPlace) {
		String dateAndTime = String.format("%s %s", showingDate, showingTime);
		EventCreation eventCreation = session.getEventCreation();
		ProvisionalShowing provisionalShowing = new ProvisionalShowing(dateAndTime, showingPlace);
		eventCreation.addShowingToEvent(provisionalShowing);

		return new RedirectView("/eventCreation/showings");
	}

	@PostMapping("eventCreation/createTicket")
	private String ticketCreation(Model model, @RequestParam int showingIndex) {
		int realIndex = showingIndex - 1;
		EventCreation eventCreation = session.getEventCreation();
		ProvisionalShowing provisionalShowing = eventCreation.getProvisionalShowings().get(realIndex);

		model.addAttribute(TicketCreationPage.SHOWING_DATE_ATTR, provisionalShowing.getShowingDate());
		model.addAttribute(TicketCreationPage.SHOWING_PLACE_ATTR, provisionalShowing.getShowingPlace());
		model.addAttribute(TicketCreationPage.SHOWING_INDEX_ATTR, realIndex);

		return TicketCreationPage.TEMPLATE_NAME;
	}

	@PostMapping("eventCreation/sendTicketData")
	private RedirectView receiveTicketData(@RequestParam int showingIndex, @RequestParam String ticketName,
			@RequestParam int ticketAmount, @RequestParam int ticketPrice) {
		EventCreation eventCreation = session.getEventCreation();
		ProvisionalTicket provisionalTicket = new ProvisionalTicket(ticketName, ticketAmount, ticketPrice);
		eventCreation.addTicketToShowing(showingIndex, provisionalTicket);

		return new RedirectView("/eventCreation/showings");
	}

	@RequestMapping("eventCreation/checkIfValid")
	private RedirectView createEventIfValid() {
		EventCreation eventCreation = session.getEventCreation();
		if (!eventCreation.isValid()) {
			return new RedirectView("/eventCreation/failed");
		}

		saveEventInDatabase();
		return new RedirectView("/eventCreation/completed");
	}

	private void saveEventInDatabase() {
		Event event = retrieveEventFromEventCreation();
		eventRepository.save(event);

		List<Showing> showings = retrieveShowingsFromEventCreation(event);
		showingRepository.saveAll(showings);

		List<Ticket> tickets = retrieveTicketsFromEventCreation(showings);
		ticketRepository.saveAll(tickets);
	}

	private Event retrieveEventFromEventCreation() {
		EventCreation eventCreation = session.getEventCreation();

		ProvisionalEvent provisionalEvent = eventCreation.getProvisionalEvent();
		String eventName = provisionalEvent.getEventName();
		String eventSummary = provisionalEvent.getEventSummary();
		String eventDescription = provisionalEvent.getEventDescription();
		Seller eventSeller = madeUpSeller; // TODO: Sacar el vendedor de la sesion
		return new Event(eventName, eventSummary, eventDescription, eventSeller);
	}

	private List<Showing> retrieveShowingsFromEventCreation(Event event) {
		List<Showing> showings = new ArrayList<>();
		EventCreation eventCreation = session.getEventCreation();

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		for (ProvisionalShowing provisionalEvent : eventCreation.getProvisionalShowings()) {
			LocalDateTime showingDate = LocalDateTime.parse(provisionalEvent.getShowingDate(), dateFormat);
			ShowingID showingID = new ShowingID(showingDate, event.getEventName());
			Showing showing = new Showing(showingID, provisionalEvent.getShowingPlace(),event);

			showings.add(showing);
		}

		return showings;
	}

	private List<Ticket> retrieveTicketsFromEventCreation(List<Showing> showings) {
		List<Ticket> tickets = new ArrayList<>();
		EventCreation eventCreation = session.getEventCreation();

		List<ProvisionalShowing> provisionalShowings = eventCreation.getProvisionalShowings();
		for (int i = 0; i < showings.size(); ++i) {
			ProvisionalShowing provisionalShowing = provisionalShowings.get(i);
			Showing showing = showings.get(i);

			for (ProvisionalTicket provisionalTicket : provisionalShowing.getProvisionalTickets()) {
				TicketID ticketID = new TicketID(provisionalTicket.getTicketName(), showing);
				Ticket ticket = new Ticket(ticketID, provisionalTicket.getTicketPrice(),
						provisionalTicket.getTicketAmount());

				tickets.add(ticket);
			}
		}

		return tickets;
	}

	@RequestMapping("eventCreation/completed")
	private String completedEventCreation() {
		return EventCreatedPage.TEMPLATE_NAME;
	}

	@RequestMapping("/eventCreation/failed")
	private String failedEventCreation() {
		// TODO
		return "";
	}

	/*
	 * User registration
	 */

	private String registrationErrorMessage = null; // TODO: Hacerlo bien

	@RequestMapping("/registerUser")
	private String userRegistration(Model model) {
		model.addAttribute(RegistrationPage.ERROR_MESSAGE_ATTR, registrationErrorMessage);

		return RegistrationPage.TEMPLATE_NAME;
	}

	@PostMapping("/registerUser/sendData")
	private RedirectView receiveNewUserData(@RequestParam String username, @RequestParam String email,
			@RequestParam String password, @RequestParam String name, @RequestParam String surname,
			@RequestParam(required = false) boolean isSeller) {
		registrationErrorMessage = null;

		if (userRepository.findById(username).isPresent()) {
			registrationErrorMessage = "Error: An user with that username already exists";
			return new RedirectView("/registerUser");
		}

		User existingUserWithEmail = userRepository.findByUserEmail(email);
		if (existingUserWithEmail != null) {
			registrationErrorMessage = "Error: An user with that email already exists";
			return new RedirectView("/registerUser");
		}

		User newUser = (isSeller) ? new Seller(username, email, name, surname, password)
				: new User(username, email, username, surname, password);

		userRepository.save(newUser);
		try {
			session.logIn(newUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new RedirectView("/testHomePage");
	}
}
