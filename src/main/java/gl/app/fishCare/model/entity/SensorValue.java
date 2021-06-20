package gl.app.fishCare.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SensorValue")
public class SensorValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sensorValueId", nullable = false)
	private Long id;

	@Column(name = "value")
	private Float value;

	@Column(name = "date", nullable = false)
	private Date date;

	@ManyToOne()
	@JoinColumn(name = "sensorId")
	@JsonIgnore
	private Sensor sensor;
}
