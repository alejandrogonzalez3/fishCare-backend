package gl.app.fishCare.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gl.app.fishCare.model.entity.Hatchery;
import gl.app.fishCare.model.entity.User;

public interface HatcheryRepository extends CrudRepository<Hatchery, Long> {

	Optional<User> findByName(String name);

}