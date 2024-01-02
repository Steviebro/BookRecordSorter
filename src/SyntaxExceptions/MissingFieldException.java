package SyntaxExceptions;

public class MissingFieldException extends SyntaxException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MissingFieldException() {
		super();
	}
	
	public MissingFieldException(String message) {
		super(message);
	}

}
