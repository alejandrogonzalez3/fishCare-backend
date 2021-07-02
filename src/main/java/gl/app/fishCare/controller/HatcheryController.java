package gl.app.fishCare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public void create(@RequestParam String name, String userName) throws EntityNotFoundException, HatcheryAlreadyAssignedException {
		hatcheryService.createHatchery(name, userName);
	}

}
