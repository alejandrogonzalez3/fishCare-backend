package gl.app.fishCare.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import gl.app.fishCare.model.entity.SensorValue;

public interface SensorValueRepository extends PagingAndSortingRepository<SensorValue, Long> {

	Page<SensorValue> findBySensorId(Long sensorId, Pageable pageable);

	@Query("SELECT sv FROM SensorValue sv WHERE sv.date IN (SELECT MAX(date) from SensorValue sv2 group by sv2.sensor.name)")
	Page<SensorValue> findLastSensorValues(Pageable pageable);
}
