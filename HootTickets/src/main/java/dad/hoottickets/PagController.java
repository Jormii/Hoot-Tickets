package dad.hoottickets;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagController {
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private SesionRepository sesionRepository;
	private Usuario user;
	@PostConstruct
	   public void init() {
	      Evento e= new Evento("concierto A", Calendar.getInstance().getTime(), "Madrid", "aa");
	      Sesion s = new Sesion(Calendar.getInstance().getTime(),50.0);
	      s.setEvento(e);
	      
	      eventRepository.save(e);
	      sesionRepository.save(s);
	      
	   }
	@RequestMapping("/")
	public String home(Model model) {
		List<Evento> eventos = eventRepository.findAll();
		
		model.addAttribute("eventos", eventos);

		return "homepage";
	}
	@RequestMapping("/sesion/{num}")
	public String sesion(Model model,@PathVariable String num) {
		System.out.print(num);
		Evento evento = eventRepository.findByNombre(num);
		
		model.addAttribute("sesiones", evento.getSesiones());

		return "sesiones";
	}
	@RequestMapping("/homeSortedbyName")
	public String homeName(  Model model) {
		List<Evento> eventos = eventRepository.findAllByOrderByNombreDesc();

	    model.addAttribute("eventos", eventos);
		

		return "homepage";
	}
	@RequestMapping("/homeSortedDate")
	public String homeDate(  Model model) {
		List<Evento> eventos = eventRepository.findAllByOrderByFechaDesc();
		for(Evento e : eventos) {
			if(e.getFecha().before(Calendar.getInstance().getTime())) {
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
