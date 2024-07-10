package pe.edu.unfv.besttraveludemy.api.models.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourFlyRequest implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Positive
	@NotNull(message = "Id fly is mandatory")
	public Long id;
}
