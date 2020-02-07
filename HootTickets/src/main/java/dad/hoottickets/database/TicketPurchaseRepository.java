package dad.hoottickets.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPurchaseRepository extends JpaRepository<TicketPurchase, TicketPurchaseID> {

}
