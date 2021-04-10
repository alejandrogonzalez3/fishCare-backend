package gl.app.fishCare.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gl.app.fishCare.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);

}