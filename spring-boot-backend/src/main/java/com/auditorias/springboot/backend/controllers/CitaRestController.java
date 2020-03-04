package com.auditorias.springboot.backend.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auditorias.springboot.backend.mapper.CitaMapper;
import com.auditorias.springboot.backend.model.Cita;
import com.auditorias.springboot.backend.model.Gallery;

@CrossOrigin(origins = { "http://localhost:4200" }) // CrossOrigin es un porotocolo para comunicar peticiones que se
//realizan al navegador, desde aqui podemos controlar todo
//(metodos, direcciones)
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class CitaRestController {

	private CitaMapper citaMapper;

	public CitaRestController(CitaMapper citaMapper) {
		this.citaMapper = citaMapper;
	}

	@PostMapping("/cita")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean create(@RequestBody Cita cita) { // Como viene en formato JSON es necesario convertirlo
		citaMapper.insert(cita); // Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;
	}

	/* Returns a List of all Appointments a person manages */
	@GetMapping("/allCitas/{id}") // Para generar el endpoint
	public List<Cita> getAllCitas(@PathVariable Long id) {
		return citaMapper.getAllCitas(id);
	}
	
	/* Get Appointments related for an employee of an audited company */
	@GetMapping("/allCitasRelated/{id}") // Para generar el endpoint
	public List<Cita> getAllCitasRelated(@PathVariable Long id) {
		return citaMapper.getAllCitasRelated(id);
	}

	/* Returns a List of appointments associated with a concrete audit */
	@GetMapping("/auditCitas/{id}") // Para generar el endpoint
	public List<Cita> getAuditCitas(@PathVariable Long id) {
		return citaMapper.getAuditCitas(id);
	}

	@PostMapping("/cita/uploads")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id,
			@RequestParam("description_gallery") String description_gallery) {
		Map<String, Object> response = new HashMap<>();

		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("citasUploads").resolve(nombreArchivo).toAbsolutePath();

			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen" + nombreArchivo);
				e.printStackTrace();
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			citaMapper.uploadAppointmentImage(nombreArchivo, id, description_gallery);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Path rutaArchivo = Paths.get("citasUploads").resolve(nombreFoto).toAbsolutePath();

		Resource recurso = null;

		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	@GetMapping("/uploads/gallery/{id}")
	public List<Gallery> getGalleryCita(@PathVariable Long id) {
		return citaMapper.getGalleryCita(id);
	}

	/* Close an open Appointment */
	@PutMapping("citaUpdate/{id}")
	public boolean updateCita(@RequestBody Cita cita, @PathVariable Long id) {
		citaMapper.updateCita(cita);
		return true;
	}

	/* Close an open Appointment */
	@PutMapping("cita/{id}")
	public boolean changeCitaState(@PathVariable Long id) {
		citaMapper.changeCitaState(id);
		return true;
	}

	/* Delete Appointment */
	@DeleteMapping("/cita/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean deleteEmployeeFromAppointment(@PathVariable Long id) {
		citaMapper.deleteAppointment(id);
		return true;
	}

	/* Check Appointment Credentials */
	@GetMapping("/appointmentCredentials/{id_audit}/{id_user}") // Para generar el endpoint
	public boolean checkAppointmentCredentials(@PathVariable Long id_audit, @PathVariable Long id_user) {
		if (citaMapper.checkAppointmentCredentials(id_audit, id_user).get(0)
				.getAppointment_permit_audit_employees() == 1) {
			return true;
		} else {
			return false;
		}
	}
}
