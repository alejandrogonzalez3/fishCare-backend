package gl.app.fishCare.model.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gl.app.fishCare.model.entity.User;
import gl.app.fishCare.model.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	void signUpUser(User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		final String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setEncryptedPassword(encryptedPassword);

		userRepository.save(user);
	}

}