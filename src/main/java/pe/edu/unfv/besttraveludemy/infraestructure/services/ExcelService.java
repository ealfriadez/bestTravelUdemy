package pe.edu.unfv.besttraveludemy.infraestructure.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.unfv.besttraveludemy.domain.entities.jpa.CustomerEntity;
import pe.edu.unfv.besttraveludemy.domain.repositories.jpa.CustomerRepository;
import pe.edu.unfv.besttraveludemy.infraestructure.abastract_services.ReportService;

@Service
@Slf4j
@AllArgsConstructor
public class ExcelService implements ReportService{

	private CustomerRepository customerRepository;
	
	@Override
	public byte[] readFile() {	
		try {
			this.createReport();
			var path = Paths.get(REPORTS_PATH, String.format(FILE_NAME, LocalDate.now().getMonth())).toAbsolutePath();
			return Files.readAllBytes(path);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	private void createReport() {
		var workbook = new XSSFWorkbook();
		var sheet = workbook.createSheet(SHEET_NAME);
		
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 7000);
		sheet.setColumnWidth(2, 3000);
		
		var header = sheet.createRow(0);
		var headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		var font = workbook.createFont();
		font.setFontName(FONT_TYPE);
		font.setFontHeightInPoints((short)16);
		font.setBold(true);
		headerStyle.setFont(font);
		
		var headerCell = header.createCell(0);
		headerCell.setCellValue(COLUMN_CUSTOMER_ID);
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(1);
		headerCell.setCellValue(COLUMN_CUSTOMER_NAME);
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(2);
		headerCell.setCellValue(COLUMN_CUSTOMER_PURCHASES);
		headerCell.setCellStyle(headerStyle);
		
		var style = workbook.createCellStyle();
		style.setWrapText(true);
		
		var customers = this.customerRepository.findAll();
		var rowsPos = 1;
		
		for (CustomerEntity customerEntity : customers) {
			var row = sheet.createRow(rowsPos);
			var cell = row.createCell(0);
			cell.setCellValue(customerEntity.getDni());
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue(customerEntity.getFullName());
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue(getTotalPurchase(customerEntity));
			cell.setCellStyle(style);
			
			rowsPos ++;
		}
		
		var report = new File(String.format(REPORTS_PATH_WITH_NAME, LocalDate.now().getMonth()));
		var path = report.getAbsolutePath();
		var fileLocation = path + FILE_TYPE;
		
		try(var outputStream = new FileOutputStream(fileLocation)) {
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			log.error(CANT_CREATE_EXCELL, e);
			throw new RuntimeException();
		}
	}
	
	private static int getTotalPurchase(CustomerEntity cuEntity) {
		return cuEntity.getTotalLodgings() + cuEntity.getTotalFlights() + cuEntity.getTotalTours();
	}
	
	private static final String SHEET_NAME = "Customer total sales";
    private static final String FONT_TYPE = "Arial";
    private static final String COLUMN_CUSTOMER_ID = "id";
    private static final String COLUMN_CUSTOMER_NAME = "name";
    private static final String COLUMN_CUSTOMER_PURCHASES = "purchases";
    private static final String REPORTS_PATH_WITH_NAME = "reports/Sales-%s";
    private static final String REPORTS_PATH = "reports";
    private static final String FILE_TYPE= ".xlsx";
    private static final String FILE_NAME= "Sales-%s.xlsx";
    private static final String CANT_CREATE_EXCELL= "Cant create Excell";
}
