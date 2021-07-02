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
import gl.app.fishCare.model.repository.SensorValueRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SensorValueService {

	private final SensorService sensorService;
	private final SensorValueRepository sensorValueRepository;

	public void storeSensorValue(Float value, String sensorName) throws EntityNotFoundException {
		Sensor sensor = sensorService.getSensor(sensorName);
		SensorValueBuilder sensorValueBuilder = SensorValue.builder();

		sensorValueBuilder.value(value).sensor(sensor).date(new Date());
		sensorValueRepository.save(sensorValueBuilder.build());
	}

	public List<SensorValue> getSensorValues(String sensorName, Integer page, Integer size, String sortBy) throws EntityNotFoundException {
		Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
		Sensor sensor = sensorService.getSensor(sensorName);

		Page<SensorValue> pagedResult = sensorValueRepository.findBySensorId(sensor.getId(), paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	public SensorValue getSensorValue(Long sensorValueId) throws EntityNotFoundException {
		Optional<SensorValue> optionalSensorValue = sensorValueRepository.findById(sensorValueId);

		if (!optionalSensorValue.isPresent()) {
			throw new EntityNotFoundException("SensorValue not found");
		}

		return optionalSensorValue.get();
	}

}
