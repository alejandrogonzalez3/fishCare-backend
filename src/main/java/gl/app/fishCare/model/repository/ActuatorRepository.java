package gl.app.fishCare.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import gl.app.fishCare.model.entity.Actuator;

public interface ActuatorRepository extends CrudRepository<Actuator, Long> {

	@Query("SELECT a FROM Actuator a WHERE a.hatchery.id = :hatcheryId AND a.name = :name")
	Optional<Actuator> findByNameAndHatcheryId(@Param("hatcheryId") Long hatcheryId, @Param("name") String name);

	@Query("SELECT a FROM Actuator a WHERE a.hatchery.id = :hatcheryId")
	List<Actuator> findByHatcheryId(@Param("hatcheryId") Long hatcheryId);
}
