package pe.edu.unfv.besttraveludemy.api.controllers.error_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pe.edu.unfv.besttraveludemy.api.models.response.BaseErrorResponse;
import pe.edu.unfv.besttraveludemy.api.models.response.ErrorResponse;
import pe.edu.unfv.besttraveludemy.util.exceptions.IdNotFoundException;

//400, 401, 402 lado del cliente

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {
	
	@ExceptionHandler(IdNotFoundException.class)
	public BaseErrorResponse handleIdNotFound(IdNotFoundException exception) {
		return ErrorResponse.builder()
				.message(exception.getMessage())
				.status(HttpStatus.BAD_REQUEST.name())
				.code(HttpStatus.BAD_REQUEST.value())
				.build();
	}
}
