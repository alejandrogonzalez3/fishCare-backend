package gl.app.fishCare.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gl.app.fishCare.model.entity.Sensor;

public interface SensorRepository extends CrudRepository<Sensor, Long> {

	Optional<Sensor> findByName(String name);

	//	interface ValueAndDate{
	//		String getValue();
	//		String getDate();
	//	}
	//
	//	List<ValueAndDate> findAllBy();
}
