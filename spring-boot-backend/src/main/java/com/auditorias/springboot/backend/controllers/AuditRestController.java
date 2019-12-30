package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.mapper.AuditMapper;
import com.auditorias.springboot.backend.model.Audit;
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
	
	/*
	 * 
	 */
	@PostMapping("/audit" )
	@ResponseStatus(HttpStatus.CREATED)
	public boolean create(@RequestBody Audit audit) {	//Como viene en formato JSON es necesario convertirlo	
		auditMapper.insert(audit);	//Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;	
	}
}
