package com.auditorias.springboot.backend.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.dto.DtoReport;
import com.auditorias.springboot.backend.mapper.ReportMapper;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class ReportRestController {

	private ReportMapper reportMapper;

	public ReportRestController(ReportMapper reportMapper) {
		this.reportMapper = reportMapper;
	}

	/*
	 * Returns a List with all the reports associated
	 */
	@GetMapping("/reportAuditorBoss/{id}") // Para generar el endpoint
	public List<DtoReport> reportAuditorBoss(@PathVariable Long id) {
		return reportMapper.reportAuditorBoss(id);
	}

	@GetMapping("/report/{id}")
	public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {

		String nombreArchivo = reportMapper.getReport(id).get(0).getRoute_report();
		Path rutaArchivo = Paths.get("results").resolve(nombreArchivo).toAbsolutePath();
		
		File file = new File(rutaArchivo.toString()+".pdf");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/pdf"))
				.body(resource);
	}

}
