package gl.app.fishCare.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gl.app.fishCare.model.entity.Sensor;
import gl.app.fishCare.model.entity.SensorValue;
import gl.app.fishCare.model.entity.SensorValue.SensorValueBuilder;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.repository.SensorRepository;
import gl.app.fishCare.model.repository.SensorValueRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SensorValueService {

	private final SensorRepository sensorRepository;
	private final SensorValueRepository sensorValueRepository;

	public void storeSensorValue(Float value, Long sensorId) throws EntityNotFoundException {
		Optional<Sensor> optionalSensor = sensorRepository.findById(sensorId);
		SensorValueBuilder sensorValueBuilder = SensorValue.builder();

		if (!optionalSensor.isPresent()) {
			throw new EntityNotFoundException("Sensor not found");
		}

		sensorValueBuilder.value(value).sensor(optionalSensor.get()).date(new Date());
		sensorValueRepository.save(sensorValueBuilder.build());
	}

	public List<SensorValue> getSensorValues(Integer page, Integer size, String sortBy) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

		Page<SensorValue> pagedResult = sensorValueRepository.findAll(paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<SensorValue>();
		}
	}

}
