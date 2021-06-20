package gl.app.fishCare.model.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import gl.app.fishCare.model.entity.Actuator;
import gl.app.fishCare.model.entity.Actuator.ActuatorBuilder;
import gl.app.fishCare.model.entity.Hatchery;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.repository.ActuatorRepository;
import gl.app.fishCare.model.repository.HatcheryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActuatorService {

	private final ActuatorRepository actuatorRepository;
	private final HatcheryRepository hatcheryRepository;

	public Iterable<Actuator> getAllActuators() {
		return actuatorRepository.findAll();
	}

	public Actuator getActuator(String name) throws EntityNotFoundException {
		Optional<Actuator> optionalActuator = actuatorRepository.findByName(name);

		if (!optionalActuator.isPresent()) {
			throw new EntityNotFoundException("Actuator not found");
		}

		return optionalActuator.get();
	}


	// Comprobar: duplicate key value violates unique constraint
	public void create(String name, Long hatcheryId) throws EntityNotFoundException {
		ActuatorBuilder newActuatorBuilder = Actuator.builder();
		Optional<Hatchery> optionalHatchery = hatcheryRepository.findById(hatcheryId);

		if (!optionalHatchery.isPresent()) {
			throw new EntityNotFoundException("Hatchery not found");
		}

		Hatchery hatchery = optionalHatchery.get();
		newActuatorBuilder.name(name).hatchery(hatchery);
		Actuator newActuator = newActuatorBuilder.build();
		actuatorRepository.save(newActuator);
	}

}
