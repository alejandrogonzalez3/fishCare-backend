package gl.app.fishCare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gl.app.fishCare.model.entity.Sensor;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.service.SensorService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sensor")
public class SensorController {

	private final SensorService sensorService;

	public SensorController(SensorService sensorService) {
		super();
		this.sensorService = sensorService;
	}

	@ApiOperation(value = "Petición GET para recuperar todos los sensores")
	@GetMapping("/")
	public Iterable<Sensor> getAllSensors() {
		return sensorService.getAllSensors();
	}

	@ApiOperation(value = "Create Sensor request")
	@PostMapping("create")
	public void create(@RequestParam String name, Float maxAllowedValue, Float minAllowedValue, Long hatcheryId) throws EntityNotFoundException{
		sensorService.create(name, maxAllowedValue, minAllowedValue, hatcheryId);
	}

}
