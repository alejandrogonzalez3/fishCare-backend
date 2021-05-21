package gl.app.fishCare.model.exception;

public class HatcheryAlreadyAssignedException extends Exception {

	private static final long serialVersionUID = 1L;

	public HatcheryAlreadyAssignedException(String errorMessage) {
		super(errorMessage);
	}
}
