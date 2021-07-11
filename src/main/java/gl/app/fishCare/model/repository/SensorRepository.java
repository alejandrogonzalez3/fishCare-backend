package gl.app.fishCare.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import gl.app.fishCare.model.entity.Sensor;

public interface SensorRepository extends CrudRepository<Sensor, Long> {

	@Query("SELECT s FROM Sensor s WHERE s.hatchery.id = :hatcheryId AND s.name = :name")
	Optional<Sensor> findByNameAndHatcheryId(@Param("hatcheryId") Long hatcheryId, @Param("name") String name);
}

