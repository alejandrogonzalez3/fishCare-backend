package gl.app.fishCare.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public void readNotification(Long notificationId) throws EntityNotFoundException {
		Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);

		if (!optionalNotification.isPresent()) {
			throw new EntityNotFoundException("Notification not found");
		}

		Notification notification = optionalNotification.get();
		notification.setIsRead(true);

		notificationRepository.save(notification);
	}

	public List<Notification> getNotifications(String sensorName, Long hatcheryId, Integer page, Integer size, String sortBy) throws EntityNotFoundException {
		Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
		Sensor sensor = sensorService.getSensor(hatcheryId, sensorName);

		Page<Notification> pagedResult = notificationRepository.findBySensorId(sensor.getId(), paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	public List<Notification> getNotReadNotifications(Long hatcheryId, Integer page, Integer size, String sortBy) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

		Page<Notification> pagedResult = notificationRepository.findNotReadNotifications(hatcheryId, paging);

		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<>();
		}
	}


}