package gl.app.fishCare;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtInfo {
	private Long userId;
	private String loginName;
}