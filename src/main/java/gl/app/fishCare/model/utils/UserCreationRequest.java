package gl.app.fishCare.model.utils;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserCreationRequest {
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}