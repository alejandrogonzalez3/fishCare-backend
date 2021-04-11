package gl.app.fishCare.model.exception;

public class InvalidLoginException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLoginException(String errorMessage) {
		super(errorMessage);
	}
}