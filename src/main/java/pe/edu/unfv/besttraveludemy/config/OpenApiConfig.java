package pe.edu.unfv.besttraveludemy.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
		info = @Info(
						title = "Best Travel API", 
						version = "1.0", 
						description = "Documentation for endpoints iin Best Travel"
					)
)
public class OpenApiConfig {

}
