package pe.edu.unfv.besttraveludemy.api.models.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsResponse extends BaseErrorResponse{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> errors;
}
