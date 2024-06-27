package pe.edu.unfv.besttraveludemy.api.models.response;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelResponse implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;    
    private String name;    
    private String address;
    private Integer rating;
    private BigDecimal price;
}
