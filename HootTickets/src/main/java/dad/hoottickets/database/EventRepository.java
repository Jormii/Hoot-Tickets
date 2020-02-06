package dad.hoottickets.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findAllByOrderByEventNameDesc();

	Event findByEventName(String eventName);

	Event findByEventID(int eventID);

}