package gl.app.fishCare.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import gl.app.fishCare.model.entity.SensorValue;

public interface SensorValueRepository extends PagingAndSortingRepository<SensorValue, Long> {
}
