package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.mapper.ThreatMapper;
import com.auditorias.springboot.backend.model.Audit_Threat;

/**
 * API Rest controller for threats
 */
@CrossOrigin(origins = { "http://localhost:4200", "*" }) // CrossOrigin es un porotocolo para comunicar peticiones que
															// se realizan al navegador, desde aqui podemos controlar
															// todo (metodos, direcciones)
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class ThreatRestController {

	private ThreatMapper threatMapper;

	private ThreatRestController(ThreatMapper threatMapper) {
		this.threatMapper = threatMapper;
	}

	/**
	 * Returns a List with all the questions
	 */
	@GetMapping("/threats/{id}") // Para generar el endpoint
	public List<Audit_Threat> threatsByAsset(@PathVariable Long id) {
		return threatMapper.threatsByAsset(id);
	}

}
