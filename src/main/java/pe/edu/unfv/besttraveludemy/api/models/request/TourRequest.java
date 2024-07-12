package pe.edu.unfv.besttraveludemy.api.models.request;

import java.io.Serializable;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourRequest implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Size(min = 18, max = 20, message = "The size have to a length btween 18 and 20 characters")
	@NotBlank(message = "Id client is mandatory")
	public String customerId;
	@Size(min = 1, message = "Min flight tour per tour")
	private Set<TourFlyRequest> flights;
	@Size(min = 1, message = "Min hotel tour per tour")
	private Set<TourHotelRequest> hotels;
	@Email(message = "Invalid email")
	private String email;
}
