package gl.app.fishCare.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // necesario para cargarlo desde @ConfigurationProperties - el @AllArgsConstructor lo quita
@Entity
@Table(name = "Hatchery")
public class Hatchery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hatcheryId", nullable = false)
	private Long id;

	@Column(name = "name", unique = true, nullable = false, length = 100)
	private String name;

	@OneToOne(mappedBy = "hatchery")
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "hatchery", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Sensor> sensors;

	@OneToMany(mappedBy = "hatchery", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Actuator> actuators;

}
