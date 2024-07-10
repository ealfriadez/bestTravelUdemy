package pe.edu.unfv.besttraveludemy.util.exceptions;

public class ForbiddenCustomerException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForbiddenCustomerException() {
		super("This customer is blocked");		
	}
}
