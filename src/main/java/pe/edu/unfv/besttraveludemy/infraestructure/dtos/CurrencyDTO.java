package pe.edu.unfv.besttraveludemy.infraestructure.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurrencyDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "date")
	private LocalDate exchangeDate;
	private Map<Currency, BigDecimal> rates;
}
