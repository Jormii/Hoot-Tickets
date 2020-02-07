package dad.hoottickets;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		String userUsername = "Nombre de usuario del usuario";
		String userEmail = "Correo del usuario";
		String userName = "Nombre del usuario";
		String userSurname = "Apellido del usuario";
		String userPassword = "Contraseña del usuario";
		User user = new User(userUsername, userEmail, userName, userSurname, userPassword);

		userRepository.save(user);

		String sellerUsername = "Nombre de usuario del vendedor";
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
		int ticketAvailableSeats = 40;
		Ticket ticket = new Ticket(ticketID, ticketPrice, ticketTotalSeats, ticketAvailableSeats);

		ticketRepository.save(ticket);

		TicketPurchaseID ticketPurchaseID = new TicketPurchaseID(user, ticket);
		int quantity = 1;
		TicketPurchase ticketPurchase = new TicketPurchase(ticketPurchaseID, quantity);

		ticketPurchaseRepository.save(ticketPurchase);
	}

	@RequestMapping("/testEventPage")
	private String eventPageTest(Model model) {
		Event event = eventRepository.findById(1).get();
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

	@RequestMapping("/testCheckoutPage")
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

	@RequestMapping("/testFinishedCheckoutPage")
	private String finishedCheckoutPage(Model model) {
		return TemplatesAttributes.FinishedCheckoutPage.TEMPLATE_NAME;
	}

	/*
	 * @RequestMapping("/") public String home(Model model) { List<Event> eventos =
	 * eventRepository.findAll();
	 * 
	 * model.addAttribute("eventos", eventos);
	 * 
	 * return "homepage"; }
	 * 
	 * @RequestMapping("/sesion/{num}") public String sesion(Model
	 * model, @PathVariable String num) { System.out.print(num); Event evento =
	 * eventRepository.findByNombre(num);
	 * 
	 * model.addAttribute("sesiones", evento.getSesiones());
	 * 
	 * return "sesiones"; }
	 * 
	 * @RequestMapping("/homeSortedbyName") public String homeName(Model model) {
	 * List<Event> eventos = eventRepository.findAllByOrderByNombreDesc();
	 * 
	 * model.addAttribute("eventos", eventos);
	 * 
	 * return "homepage"; }
	 * 
	 * @RequestMapping("/homeSortedDate") public String homeDate(Model model) {
	 * List<Event> eventos = eventRepository.findAllByOrderByFechaDesc(); for (Event
	 * e : eventos) { if (e.getFecha().before(Calendar.getInstance().getTime())) {
	 * eventos.remove(e); } } // solo muestra los eventos futuros
	 * model.addAttribute("eventos", eventos);
	 * 
	 * return "homepage"; }
	 * 
	 * @RequestMapping("/buscarEvento") public String buscar(@RequestParam(value =
	 * "buscar") String buscar, Model model) { Event e =
	 * eventRepository.findByNombre(buscar);
	 * 
	 * model.addAttribute("eventos", e);
	 * 
	 * return "homepage"; }
	 */
}
