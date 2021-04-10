package gl.app.fishCare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/example")
public class ExampleController {

	@ApiOperation(value = "Petición GET de ejemplo")
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}