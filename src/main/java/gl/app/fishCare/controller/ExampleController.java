package gl.app.fishCare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/example")
public class ExampleController {

	@ApiOperation(value = "Petición GET de ejemplo", authorizations = {@Authorization(value = "Bearer")})
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}