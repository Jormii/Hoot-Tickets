package dad.hoottickets.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowingRepository extends JpaRepository<Showing, ShowingID> {

	// TODO: Produce error. Crear query manual
	// Showing findByShowingEventID(int eventID);

}