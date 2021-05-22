package gl.app.fishCare.model.repository;

import org.springframework.data.repository.CrudRepository;

import gl.app.fishCare.model.entity.SensorValue;

public interface SensorValueRepository extends CrudRepository<SensorValue, Long> {

}
