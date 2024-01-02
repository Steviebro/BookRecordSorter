package SemanticExceptions;

public class BadPriceException extends SemanticException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadPriceException() {
		super();
	}
	
	public BadPriceException(String message) {
		super(message);
	}

}
