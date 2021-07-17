package gl.app.fishCare.model.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gl.app.fishCare.model.entity.User;
import gl.app.fishCare.model.exception.InvalidLoginException;
import gl.app.fishCare.model.repository.UserRepository;
import gl.app.fishCare.model.utils.JwtUtils;
import gl.app.fishCare.model.utils.UserCreationResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public UserCreationResponse signUpUser(User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		final String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setEncryptedPassword(encryptedPassword);

		// TODO: Avoid to save this field in the Database
		user.setPassword(null);

		user = userRepository.save(user);

		String jwt = JwtUtils.getJWTToken(user.getUserName(), user.getId());

		UserCreationResponse userCreationResponse = UserCreationResponse.builder().email(user.getEmail()).id(user.getId()).userName(user.getUserName()).jwt(jwt).build();
		return userCreationResponse;
	}

	public User login(String username, String password) throws InvalidLoginException {
		new BCryptPasswordEncoder();
		Optional<User> optionalUser = userRepository.findByUserName(username);

		if (!optionalUser.isPresent()) {
			throw new InvalidLoginException("The user does not exist.");
		}

		User user = optionalUser.get();

		if (!BCrypt.checkpw(password, user.getEncryptedPassword())) {
			throw new InvalidLoginException("Password does not match.");
		}

		return user;

	}

}