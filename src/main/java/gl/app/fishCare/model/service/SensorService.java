package gl.app.fishCare.model.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import gl.app.fishCare.model.entity.Hatchery;
import gl.app.fishCare.model.entity.Sensor;
import gl.app.fishCare.model.entity.Sensor.SensorBuilder;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.repository.HatcheryRepository;
import gl.app.fishCare.model.repository.SensorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SensorService {

	private final SensorRepository sensorRepository;
	private final HatcheryRepository hatcheryRepository;

	public Iterable<Sensor> getAllSensors() {
		return sensorRepository.findAll();
	}

	public Sensor getSensor(Long hatcheryId, String name) throws EntityNotFoundException {
		Optional<Sensor> optionalSensor = sensorRepository.findByNameAndHatcheryId(hatcheryId, name);

		if (!optionalSensor.isPresent()) {
			throw new EntityNotFoundException("Sensor not found");
		}

		return optionalSensor.get();
	}


	// Comprobar: duplicate key value violates unique constraint
	public void create(String name, Float maxAllowedValue, Float minAllowedValue, Long hatcheryId, String units) throws EntityNotFoundException {
		SensorBuilder newSensorBuilder = Sensor.builder();
		Optional<Hatchery> optionalHatchery = hatcheryRepository.findById(hatcheryId);

		if (!optionalHatchery.isPresent()) {
			throw new EntityNotFoundException("Hatchery not found");
		}

		Hatchery hatchery = optionalHatchery.get();
		newSensorBuilder.maxAllowedValue(maxAllowedValue).minAllowedValue(minAllowedValue).name(name).hatchery(hatchery).units(units);
		Sensor newSensor = newSensorBuilder.build();
		sensorRepository.save(newSensor);
	}
}
