package com.example.demo.cotrollers;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/reports")
public class ReportController {

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateReport() {
        try {
            // Load the Jasper report template
            InputStream reportStream = getClass().getResourceAsStream("/reports/report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Create report parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", "My First Report");

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            // Export the report to a PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            // Return the PDF as a response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline").filename("report.pdf").build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("Error : {0}",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
