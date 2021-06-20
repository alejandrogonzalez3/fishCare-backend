package gl.app.fishCare.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gl.app.fishCare.model.entity.Actuator;

public interface ActuatorRepository extends CrudRepository<Actuator, Long> {

	Optional<Actuator> findByName(String name);

}
