package gl.app.fishCare.model.exception;

public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String errorMessage) {
		super(errorMessage);

	}
}
