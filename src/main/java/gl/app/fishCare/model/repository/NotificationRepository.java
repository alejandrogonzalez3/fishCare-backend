package gl.app.fishCare.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import gl.app.fishCare.model.entity.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

	@Query("SELECT n FROM Notification n WHERE n.sensorValue.sensor.id=:sensorId")
	Page<Notification> findBySensorId(@Param("sensorId") Long sensorId, Pageable pageable);

}

