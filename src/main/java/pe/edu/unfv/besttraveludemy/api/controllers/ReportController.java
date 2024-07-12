package pe.edu.unfv.besttraveludemy.api.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ReportService;

@RestController
@RequestMapping(path = "report")
@AllArgsConstructor
@Tag(name = "Report")
public class ReportController {

	private final ReportService reportService;
	
	@GetMapping
	public ResponseEntity<Resource> get(){
		var headers = new HttpHeaders();
		headers.setContentType(FORCE_DOWNLOAD);
		headers.set(HttpHeaders.CONTENT_DISPOSITION, FORCE_DOWNLOAD_HEADER_VALUE);
		
		var fileInBytes = this.reportService.readFile();
		ByteArrayResource response = new ByteArrayResource(fileInBytes);
		
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(fileInBytes.length)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(response);
	}
	
	private static final MediaType FORCE_DOWNLOAD = new MediaType("application", "force-download");
	private static final String FORCE_DOWNLOAD_HEADER_VALUE = "attachment; filename=report.xlsx";
}
