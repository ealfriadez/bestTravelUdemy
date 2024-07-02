package pe.edu.unfv.besttraveludemy.api.models.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourHotelRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long id;
	private Integer totalDays;
}
