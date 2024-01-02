package SyntaxExceptions;

public class TooFewFieldsException extends SyntaxException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TooFewFieldsException() {
		super();
	}
	
	public TooFewFieldsException(String message) {
		super(message);
	}

}
