package gl.app.fishCare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gl.app.fishCare.model.entity.User;
import gl.app.fishCare.model.exception.InvalidLoginException;
import gl.app.fishCare.model.service.UserService;
import gl.app.fishCare.model.utils.JwtUtils;
import gl.app.fishCare.model.utils.UserCreationRequest;
import gl.app.fishCare.model.utils.UserCreationResponse;
import gl.app.fishCare.model.utils.UserRole;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Incorrect username or password")
	@ExceptionHandler({ InvalidLoginException.class })
	public void handleException() {
		// Nothing to do
	}

	@ApiOperation(value = "SignUp Request")
	@PostMapping("signup")
	public UserCreationResponse signUp(@RequestBody UserCreationRequest body) {
		return userService.signUpUser(User.builder().userName(body.getUserName()).email(body.getEmail()).password(body.getPassword()).userRole(UserRole.USER).build());
	}

	@ApiOperation(value = "Login Request")
	@PostMapping("login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password)
			throws InvalidLoginException {

		User user = userService.login(username, password);

		return JwtUtils.getJWTToken(username, user.getId());

	}

}
