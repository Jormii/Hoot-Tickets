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

		int eventID = 0;
		String eventName = "Nombre evento";
		String eventSummary = "Resumen evento";
		String eventDescription = "Descripción evento";
		Event event = new Event(eventID, eventName, eventSummary, eventDescription, seller);

		eventRepository.save(event);

		ShowingID showingID = new ShowingID(new Date(), eventID);
		String showingPlace = "Lugar de la sesion";
		Showing showing = new Showing(showingID, showingPlace);

		showingRepository.save(showing);

		String ticketName = "Nombre de la entrada";
		TicketID ticketID = new TicketID(ticketName, showingID);
		int ticketPrice = 10;
		int ticketTotalSeats = 100;
		int ticketAvailableSeats = 40;
		Ticket ticket = new Ticket(ticketID, ticketPrice, ticketTotalSeats, ticketAvailableSeats);

		ticketRepository.save(ticket);
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
