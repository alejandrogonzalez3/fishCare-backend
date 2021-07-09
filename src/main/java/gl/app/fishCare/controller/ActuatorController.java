package gl.app.fishCare.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gl.app.fishCare.model.entity.Actuator;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.service.ActuatorService;
import gl.app.fishCare.mqtt.MqttMessageClient;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/actuator")
public class ActuatorController {
	@Value("${mqtt.topic}")
	private String baseTopic;

	private final ActuatorService actuatorService;
	private MqttMessageClient mqttMessageClient;

	public ActuatorController(ActuatorService actuatorService, MqttMessageClient mqttMessageClient) {
		super();
		this.actuatorService = actuatorService;
		this.mqttMessageClient = mqttMessageClient;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Actuator not found")
	@ExceptionHandler({ EntityNotFoundException.class })
	public void handleException() {
		// Nothing to do
	}

	@ApiOperation(value = "Petición GET para recuperar todos los actuadores")
	@GetMapping("all")
	public Iterable<Actuator> getAllActuators() {
		return actuatorService.getAllActuators();
	}

	@ApiOperation(value = "Create Actuator request")
	@PostMapping("create")
	public void create(@RequestParam String name, Long hatcheryId) throws EntityNotFoundException{
		actuatorService.create(name, hatcheryId);
	}

	@ApiOperation(value = "Publicacion de un mensaje de encendido con el protocolo MQTT a cierto actuador")
	@PostMapping("on")
	public void actuatorOnMqtt(@RequestParam String name) throws EntityNotFoundException {
		actuatorService.getActuator(name);
		String topic = baseTopic + "/" + name;
		System.out.println(topic);

		mqttMessageClient.sendMessage(topic, "on");
	}

	@ApiOperation(value = "Publicacion de un mensaje de apagado con el protocolo MQTT a cierto actuador")
	@PostMapping("off")
	public void actuatorOffMqtt(@RequestParam String name) throws EntityNotFoundException {
		actuatorService.getActuator(name);
		String topic = baseTopic + "/" + name;
		System.out.println(topic);

		mqttMessageClient.sendMessage(topic, "off");
	}

}
