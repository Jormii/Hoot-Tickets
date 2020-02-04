package dad.hoottickets;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagController {
	@Autowired
	private EventRepository eventRepository;
	private Usuario user;
	@RequestMapping("/home")
	public String home(Model model) {
		List<Evento> eventos = eventRepository.findAll();

		model.addAttribute("eventos", eventos);

		return "homepage";
	}
	@RequestMapping("/homeSortedbyName")
	public String homeName(  Model model) {
		List<Evento> eventos = eventRepository.findAllByOrderByNombreDesc();

	    model.addAttribute("eventos", eventos);
		

		return "homepage";
	}
	@RequestMapping("/homeSortedDate")
	public String homeDate(  Model model) {
		List<Evento> eventos = eventRepository.findAllByOrderByDateDesc();
		for(Evento e : eventos) {
			if(e.getDate().before(Calendar.getInstance().getTime())) {
			 eventos.remove(e);
			}
		}// solo muestra los eventos futuros
	    model.addAttribute("eventos", eventos);
		

		return "homepage";
	}
	@RequestMapping("/buscarEvento")
	public String buscar( @RequestParam(value= "buscar") String buscar, Model model) {
		Evento e = eventRepository.findByNombre(buscar);

	    model.addAttribute("eventos", e);
		

		return "homepage";
	}
}
