package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.mapper.AuditMapper;
import com.auditorias.springboot.backend.model.Audit_Type;


@CrossOrigin(origins = { "http://localhost:4200" }) // CrossOrigin es un porotocolo para comunicar peticiones que se
// realizan al navegador, desde aqui podemos controlar todo
// (metodos, direcciones)
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class AuditRestController {

	private AuditMapper auditMapper;

	public AuditRestController(AuditMapper auditMapper) {
		this.auditMapper = auditMapper;
	}
	
	/**
	 * Returns a List with all the possible types to audit 
	 * @link      Audit_Type
	 */
	@GetMapping("/auditTypes") 
	public List<Audit_Type> getAll() {
		return auditMapper.findAuditTypes();
	}
}
