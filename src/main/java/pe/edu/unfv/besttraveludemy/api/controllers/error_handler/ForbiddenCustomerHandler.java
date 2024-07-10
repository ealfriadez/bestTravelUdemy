package pe.edu.unfv.besttraveludemy.api.controllers.error_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pe.edu.unfv.besttraveludemy.api.models.response.BaseErrorResponse;
import pe.edu.unfv.besttraveludemy.api.models.response.ErrorResponse;
import pe.edu.unfv.besttraveludemy.util.exceptions.ForbiddenCustomerException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenCustomerHandler {

	@ExceptionHandler(ForbiddenCustomerException.class)
	public BaseErrorResponse handleIdNotFound(ForbiddenCustomerException exception) {
		return ErrorResponse.builder()
				.message(exception.getMessage())
				.status(HttpStatus.FORBIDDEN.name())
				.code(HttpStatus.FORBIDDEN.value())
				.build();
	}
}
