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


	// Comprobar: duplicate key value violates unique constraint
	public void create(String name, Float maxAllowedValue, Float minAllowedValue, Long hatcheryId) throws EntityNotFoundException {
		SensorBuilder newSensor = Sensor.builder();
		Optional<Hatchery> optionalHatchery = hatcheryRepository.findById(hatcheryId);

		if (!optionalHatchery.isPresent()) {
			throw new EntityNotFoundException("Hatchery not found");
		}

		newSensor.maxAllowedValue(maxAllowedValue).minAllowedValue(minAllowedValue).name(name);
		sensorRepository.save(newSensor.build());
	}
}
