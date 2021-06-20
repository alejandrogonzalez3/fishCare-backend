package gl.app.fishCare.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor // necesario para cargarlo desde @ConfigurationProperties - el @AllArgsConstructor lo quita
@Entity
@Table(name = "Hatchery")
public class Hatchery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", unique = true, nullable = false, length = 100)
	private String name;

	@OneToOne(mappedBy = "hatchery")
	private User user;

}
