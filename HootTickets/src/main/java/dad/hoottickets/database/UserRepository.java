package dad.hoottickets.database;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

	User findByUserUsername(String username);

	User findByUserEmail(String email);

}