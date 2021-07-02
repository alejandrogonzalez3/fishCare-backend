package gl.app.fishCare.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gl.app.fishCare.model.entity.Notification;
import gl.app.fishCare.model.entity.Notification.NotificationBuilder;
import gl.app.fishCare.model.entity.Sensor;
import gl.app.fishCare.model.entity.SensorValue;
import gl.app.fishCare.model.exception.EntityNotFoundException;
import gl.app.fishCare.model.repository.NotificationRepository;
import gl.app.fishCare.model.utils.NotificationType;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationService {

	private final SensorValueService sensorValueService;
	private final SensorService sensorService;
	private final NotificationRepository notificationRepository;

	public void createNotification(Long sensorValueId, NotificationType notificationType) throws EntityNotFoundException {
		SensorValue sensorValue = sensorValueService.getSensorValue(sensorValueId);

		NotificationBuilder notificationBuilder = Notification.builder();
		notificationBuilder.notificationType(notificationType).sensorValue(sensorValue);

		notificationRepository.save(notificationBuilder.build());
	}

	public List<Notification> getNotifications(String sensorName, Integer page, Integer size, String sortBy) throws EntityNotFoundException {
		Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
		Sensor sensor = sensorService.getSensor(sensorName);

		Page<Notification> pagedResult = notificationRepository.findBySensorId(sensor.getId(), paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<>();
		}
	}

}