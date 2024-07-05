package pe.edu.unfv.besttraveludemy.api.models.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private Integer code;
}
