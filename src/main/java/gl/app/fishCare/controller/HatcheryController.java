package gl.app.fishCare.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gl.app.fishCare.model.entity.Hatchery;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.exception.HatcheryAlreadyAssignedException;
import gl.app.fishCare.model.service.HatcheryService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hatchery")
public class HatcheryController {

	private final HatcheryService hatcheryService;

	public HatcheryController(HatcheryService hatcheryService) {
		super();
		this.hatcheryService = hatcheryService;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
	@ExceptionHandler({ EntityNotFoundException.class })
	public void handleNotFoundException() {
		// Nothing to do
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Hatchery already assigned")
	@ExceptionHandler({ HatcheryAlreadyAssignedException.class })
	public void handleAlreadyExistException() {
		// Nothing to do
	}

	// Checar quen pode facer isto (ou a maneira de restrinxilo)
	@ApiOperation(value = "Create Hatchery request")
	@PostMapping("create")
	public Hatchery create(@RequestParam Long userId, String name) throws EntityNotFoundException, HatcheryAlreadyAssignedException {
		return hatcheryService.createHatchery(userId, name);
	}

	@ApiOperation(value = "Petición GET para recuperar el vivero de un usuario concreto")
	@GetMapping("/user")
	public Hatchery getUserHatchery(@RequestAttribute Long userId) throws EntityNotFoundException {
		return hatcheryService.getUserHatchery(userId);
	}

	@ApiOperation(value = "Get default auto actuators behaviour")
	@GetMapping("actuators")
	public Map<String, Boolean> getDefaultActuatorsBehaviour(@RequestParam Long hatcheryId) throws EntityNotFoundException {
		return hatcheryService.getDefaultActuatorsBehaviour(hatcheryId);
	}

	@ApiOperation(value = "Change oxygenator default behaviour")
	@PostMapping("defaultOxygenator")
	public void setOxygenatorDefaultBehaviour(@RequestParam Long hatcheryId, Boolean defaultBehaviour) throws EntityNotFoundException {
		hatcheryService.setOxygenatorDefaultBehaviour(hatcheryId, defaultBehaviour);
	}

	@ApiOperation(value = "Change waterPump default behaviour")
	@PostMapping("defaultWaterPump")
	public void setWaterPumpDefaultBehaviour(@RequestParam Long hatcheryId, Boolean defaultBehaviour) throws EntityNotFoundException {
		hatcheryService.setWaterPumpDefaultBehaviour(hatcheryId, defaultBehaviour);
	}

}
