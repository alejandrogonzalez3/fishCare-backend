package gl.app.fishCare.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import gl.app.fishCare.model.utils.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor // necesario para cargarlo desde @ConfigurationProperties - el @AllArgsConstructor lo quita
@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId", nullable = false)
	private Long id;

	@Column(name = "userName", nullable = false, length = 100)
	private String userName;

	@Column(name = "firstName", length = 100)
	private String firstName;

	@Column(name = "lastName", length = 100)
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password", length = 100)
	private String password;

	@Column(name = "encryptedPassword", nullable = false, length = 100)
	private String encryptedPassword;

	@Column(name = "userRole", nullable = false)
	private UserRole userRole = UserRole.USER;
}