package dad.hoottickets;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionRepository extends JpaRepository<Sesion, Long>{
	Sesion findByIdsesion(int buscar);
}
