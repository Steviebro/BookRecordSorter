package SyntaxExceptions;

public class TooManyFieldsException extends SyntaxException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TooManyFieldsException() {
		super();
	}
	
	public TooManyFieldsException(String message) {
		super(message);
	}

}
