package gl.app.fishCare.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}