package gl.app.fishCare.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gl.app.fishCare.model.utils.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationId", nullable = false)
	private Long id;

	@Column(name = "notificationType", nullable = false)
	private NotificationType notificationType;

	@Column(name = "isRead", nullable = false)
	@Builder.Default
	private Boolean isRead = false;

	@ManyToOne()
	@JoinColumn(name = "sensorValueId")
	private SensorValue sensorValue;

}
