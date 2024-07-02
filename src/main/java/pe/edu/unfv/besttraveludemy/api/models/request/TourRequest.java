package pe.edu.unfv.besttraveludemy.api.models.request;

import java.io.Serializable;
import java.util.Set;

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
	public String customerId;
	private Set<TourFlyRequest> flights;
	private Set<TourHotelRequest> hotels;
}
