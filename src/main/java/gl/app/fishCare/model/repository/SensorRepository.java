package gl.app.fishCare.model.repository;

import org.springframework.data.repository.CrudRepository;

import gl.app.fishCare.model.entity.Sensor;

public interface SensorRepository extends CrudRepository<Sensor, Long> {

	//	interface ValueAndDate{
	//		String getValue();
	//		String getDate();
	//	}
	//
	//	List<ValueAndDate> findAllBy();
}
