package pe.edu.unfv.besttraveludemy.util.exceptions;

public class IdNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private static final String ERROR_MESSAGE = "Record no exist in %s";
	
	public IdNotFoundException(String tableName) {
		super(String.format(ERROR_MESSAGE, tableName));
	}
}
