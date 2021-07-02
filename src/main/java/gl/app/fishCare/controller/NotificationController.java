package gl.app.fishCare.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gl.app.fishCare.model.entity.Notification;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.service.NotificationService;
import gl.app.fishCare.model.utils.NotificationType;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}

	@ApiOperation(value = "Petición GET para recuperar de manera paginada las notificaciones de un sensor")
	@GetMapping("/")
	public List<Notification> getNotifications(@RequestParam String sensorName,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "id") String sortBy) throws EntityNotFoundException {

		return notificationService.getNotifications(sensorName, page, size, sortBy);
	}

	@ApiOperation(value = "Create notification")
	@PostMapping("create")
	public void create(@RequestParam Long sensorValueId, NotificationType notificationType)
			throws EntityNotFoundException {
		notificationService.createNotification(sensorValueId, notificationType);
	}
}
