package dad.hoottickets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Evento, Long> {
	List<Evento> findAllByOrderByNombreDesc();
	List<Evento> findAllByOrderByFechaDesc();
	Evento findByNombre(String buscar);
	Evento findByIdevento(int buscar);

}
