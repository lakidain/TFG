package com.auditorias.springboot.backend.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.auditorias.springboot.backend.dto.DtoReport;
import com.auditorias.springboot.backend.mapper.ReportMapper;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class ReportRestController {

	private ReportMapper reportMapper;

	public ReportRestController(ReportMapper reportMapper) {
		this.reportMapper = reportMapper;
	}

	/*
	 * Returns a List with all the reports associated with AuditorBoss company
	 */
	@GetMapping("/reportAuditorBoss/{id}") // Para generar el endpoint
	public List<DtoReport> reportAuditorBoss(@PathVariable Long id) {
		return reportMapper.reportAuditorBoss(id);
	}

	/*
	 * Returns a List with all the reports associated with AuditedBoss company
	 */
	@GetMapping("/reportAuditedBoss/{id}") // Para generar el endpoint
	public List<DtoReport> reportAuditedBoss(@PathVariable Long id) {
		return reportMapper.reportAuditedBoss(id);
	}

	/*
	 * Returns a List with all the reports associated with an Auditor
	 */
	@GetMapping("/reportAuditor/{id}") // Para generar el endpoint
	public List<DtoReport> reportAuditor(@PathVariable Long id) {
		return reportMapper.reportAuditor(id);
	}

	/*
	 * Returns a List with all the reports associated with an employee from an
	 * Audited company
	 */
	@GetMapping("/reportAudited/{id}") // Para generar el endpoint
	public List<DtoReport> reportAudited(@PathVariable Long id) {
		return reportMapper.reportAudited(id);
	}

	@GetMapping("/report/{nombreArchivo}")
	public ResponseEntity<Resource> download(@PathVariable String nombreArchivo) throws IOException {
		
		/* 
	 	Path rutaArchivo = Paths.get("results").resolve(nombreArchivo).toAbsolutePath();

		File file = new File(rutaArchivo.toString() + ".pdf");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/pdf")).body(resource);*/

		AWSCredentials credentials = new BasicAWSCredentials("Access Key ID",
				"Secret Access Key");
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("eu-west-3")
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

		S3Object object = s3Client.getObject("upaudit/pdf",
				nombreArchivo + ".pdf");
		S3ObjectInputStream s3is = object.getObjectContent();

		return ResponseEntity.ok().contentType(org.springframework.http.MediaType.APPLICATION_PDF)
				.cacheControl(CacheControl.noCache())
				.header("Content-Disposition", "attachment; filename=" + nombreArchivo + ".pdf")
				.body(new InputStreamResource(s3is));
	}

}
