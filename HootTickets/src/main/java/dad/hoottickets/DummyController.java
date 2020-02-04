package dad.hoottickets;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DummyController {

	@RequestMapping("/dummy")
	public String dummyController(Model model) {
		return eventPageTest(model);
	}

	private String eventPageTest(Model model) {
		String eventName = "Nombre del evento";
		String eventSummary = "Resumen del evento";
		String eventDescription = "Descripción del evento";
		List<Ticket> eventDaysList = Arrays.asList(new Ticket(0, new TimeAndLocation(new Date(), "Lugar 1")),
				new Ticket(1, new TimeAndLocation(new Date(), "Lugar 2")),
				new Ticket(2, new TimeAndLocation(new Date(), "Lugar 3")));

		model.addAttribute(TemplatesTags.EventPageTags.EVENT_NAME_TAG, eventName);
		model.addAttribute(TemplatesTags.EventPageTags.EVENT_SUMMARY_TAG, eventSummary);
		model.addAttribute(TemplatesTags.EventPageTags.EVENT_DESCRIPTION_TAG, eventDescription);
		model.addAttribute(TemplatesTags.EventPageTags.EVENT_DAYS_LIST_TAG, eventDaysList);
		return TemplatesTags.EventPageTags.TEMPLATE_NAME;
	}

}
