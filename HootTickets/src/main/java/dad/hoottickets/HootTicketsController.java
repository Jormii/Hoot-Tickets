package dad.hoottickets;

import java.util.Calendar;
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
import dad.hoottickets.database.Showing;
import dad.hoottickets.database.User;

@Controller
public class HootTicketsController {

	@Autowired
	private EventRepository eventRepository;

	@PostConstruct
	public void init() {
		Event event_0 = new Event(0, "Nombre evento 0", "Resumen evento 0", "Descripción evento 0");
		Event event_1 = new Event(1, "Nombre evento 1", "Resumen evento 1", "Descripción evento 1");
		Event event_2 = new Event(2, "Nombre evento 2", "Resumen evento 2", "Descripción evento 2");

		eventRepository.save(event_0);
		eventRepository.save(event_1);
		eventRepository.save(event_2);
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
