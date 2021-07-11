package gl.app.fishCare.model.entity;

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
@NoArgsConstructor // necesario para cargarlo desde @ConfigurationProperties - el @AllArgsConstructor lo quita
@Entity
@Table(name = "Actuator")
public class Actuator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actuatorId", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@ManyToOne()
	@JoinColumn(name = "hatcheryId")
	@JsonIgnore
	private Hatchery hatchery;

}
