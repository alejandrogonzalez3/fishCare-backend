package gl.app.fishCare.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gl.app.fishCare.mqtt.MqttMessageClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/example")
public class ExampleController {

	@Resource
	private MqttMessageClient mqttMessageClient;

	@ApiOperation(value = "Petición GET de ejemplo", authorizations = {@Authorization(value = "Bearer")})
	@GetMapping("/")
	public String greetings() {
		return "Greetings from Spring Boot!";
	}

	@ApiOperation(value = "Publicacion de un mensaje de prueba con el protocolo MQTT")
	@PostMapping("/")
	public void mqtt() {
		mqttMessageClient.sendMessage("fishCare", "contenido de ejemplo");
	}

}