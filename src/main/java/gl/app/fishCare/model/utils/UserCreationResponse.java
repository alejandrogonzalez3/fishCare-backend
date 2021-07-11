package gl.app.fishCare.model.utils;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserCreationResponse {
	private Long id;
	private String userName;
	private String email;
}