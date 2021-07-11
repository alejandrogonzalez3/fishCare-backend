package gl.app.fishCare.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gl.app.fishCare.model.entity.SensorValue;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.service.SensorService;
import gl.app.fishCare.model.service.SensorValueService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sensorValue")
public class SensorValueController {

	private final SensorValueService sensorValueService;
	private final SensorService sensorService;

	public SensorValueController(SensorValueService sensorValueService, SensorService sensorService) {
		super();
		this.sensorValueService = sensorValueService;
		this.sensorService = sensorService;
	}

	@ApiOperation(value = "Petición GET para recuperar de manera paginada los valores de un sensor")
	@GetMapping("/")
	public List<SensorValue> getSensorValues(
			@RequestParam Long hatcheryId,
			@RequestParam String sensorName,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sortBy) throws EntityNotFoundException {
		return sensorValueService.getSensorValues(hatcheryId, sensorName, page, size, sortBy);
	}

	@ApiOperation(value = "Petición GET para recuperar de manera paginada el ultimo valor de cada sensor")
	@GetMapping("/last")
	public List<SensorValue> getLastSensorValues(
			@RequestParam Long hatcheryId,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sortBy) throws EntityNotFoundException {
		return sensorValueService.getLastSensorValues(hatcheryId, page, size, sortBy);
	}

	@ApiOperation(value = "Store Sensor value")
	@PostMapping("store")
	public void create(@RequestParam String sensorName, Float value, Long hatcheryId) throws EntityNotFoundException{
		sensorService.getSensor(hatcheryId, sensorName);
		sensorValueService.storeSensorValue(value, sensorName, hatcheryId);
	}

}
