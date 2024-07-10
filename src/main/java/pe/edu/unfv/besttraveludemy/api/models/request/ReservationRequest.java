package pe.edu.unfv.besttraveludemy.api.models.request;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Size(min = 18, max = 20, message = "The size have to a length btween 18 and 20 characters")
	@NotBlank(message = "Id client is mandatory")
	private String idClient;
	@Positive
	@NotNull(message = "Id client is mandatory")
	private Long idHotel;
	@Min(value = 1, message = "Min 1 day to make reservation")
	@Max(value = 30, message = "Max 30 days to make reservation")
	@NotNull(message = "Total days is mandatory")
	private Integer totalDays;
	@Email(message = "Invalid email")
	private String email;
}
