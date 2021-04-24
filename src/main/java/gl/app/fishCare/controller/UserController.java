package gl.app.fishCare.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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
import gl.app.fishCare.model.utils.UserCreationRequest;
import gl.app.fishCare.model.utils.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
	public void signUp(@RequestBody UserCreationRequest body) {
		userService.signUpUser(User.builder().userName(body.getUserName()).firstName(body.getFirstName())
				.lastName(body.getLastName()).email(body.getEmail()).password(body.getPassword()).userRole(UserRole.USER).build());
	}

	@ApiOperation(value = "Login Request")
	@PostMapping("login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password)
			throws InvalidLoginException {

		userService.login(username, password);

		return getJWTToken(username);

	}

	private String getJWTToken(String username) {
		// TODO: Save secretKey in another place (used also in JWTAuthorizationFilter)
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
