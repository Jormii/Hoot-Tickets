package dad.hoottickets.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPurchaseRepository extends JpaRepository<TicketPurchase, Long> {

	List<TicketPurchase> findByTicketPurchaseUniqueID_User(User user);
	
}
