package dad.hoottickets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.tools.javac.util.Pair;

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

		ShowingID showingID = new ShowingID(new Date(), event.getEventName());
		String showingPlace = "Lugar de la sesion";
		Showing showing = new Showing(showingID, showingPlace,event);

		showingRepository.save(showing);

		String ticketName = "Nombre de la entrada";
		TicketID ticketID = new TicketID(ticketName, showing);
		TicketID ticketIDA = new TicketID("ejemplo", showing);
		int ticketPrice = 10;
		int ticketTotalSeats = 100;
		int ticketAvailableSeats = 40;
		Ticket ticket = new Ticket(ticketID, ticketPrice, ticketTotalSeats, ticketAvailableSeats);
		Ticket ticket1 = new Ticket(ticketIDA, 20, 20, 20);
		ticketRepository.save(ticket);
		ticketRepository.save(ticket1);
		/*TicketPurchaseID ticketPurchaseID = new TicketPurchaseID(user, ticket);
		int quantity = 1;
		TicketPurchase ticketPurchase = new TicketPurchase(ticketPurchaseID, quantity);

		ticketPurchaseRepository.save(ticketPurchase);*/
	}

	@RequestMapping("/testHomePage")
	public String home(Model model) {
		List<Event> eventsList = eventRepository.findAll();

		model.addAttribute(TemplatesAttributes.HomePage.EVENTS_LIST_ATTR, eventsList);

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
		 SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		 Date date= formatter.parse(ShowingDate);

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
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		 Date date= formatter.parse(ShowingDate);
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
		//model.addAttribute(TemplatesAttributes.CheckoutPage.TICKETS_SELECTED_QUA,);
		return TemplatesAttributes.CheckoutPage.TEMPLATE_NAME;
	}

	@PostMapping("/testFinishedCheckoutPage")
	private String finishedCheckoutPage(Model model,@RequestParam("seat[]") List<Integer> to,@RequestParam String ShowingDate,@RequestParam String ShowingEvent) throws ParseException {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		 Date date= formatter.parse(ShowingDate);
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

}
