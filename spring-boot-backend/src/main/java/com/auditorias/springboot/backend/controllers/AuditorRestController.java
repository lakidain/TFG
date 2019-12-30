package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.mapper.AuditorMapper;
import com.auditorias.springboot.backend.model.Auditor;

@CrossOrigin(origins= {"http://localhost:4200"}) //CrossOrigin es un porotocolo para comunicar peticiones que se realizan al navegador, desde aqui podemos controlar todo (metodos, direcciones)
@RestController //Como no va a tener vista
@RequestMapping("/api") //Aqui nos generara la url
public class AuditorRestController {

	private AuditorMapper auditorMapper;
	
	public AuditorRestController(AuditorMapper auditorMapper) {
		this.auditorMapper = auditorMapper;
	}
	
	@GetMapping("/auditores") //Para generar el endpoint
	public List <Auditor> getAll() {	
		return auditorMapper.findAll();
	}
	
	@GetMapping("/authenticate/{dni}/{password}") //Para generar el endpoint
	public Auditor authenticate(@PathVariable String dni, @PathVariable String password) {	
		return auditorMapper.authenticate(dni, password).get(0);
	}
	
	@PostMapping("/auditores" )
	@ResponseStatus(HttpStatus.CREATED)
	public Auditor create(@RequestBody Auditor auditor) {	//Como viene en formato JSON es necesario convertirlo	
		auditorMapper.insert(auditor);	//Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return auditorMapper.searchByDni(auditor.getDni()).get(0);	
	}
	
	/*
	@PutMapping("/auditores/{dni}")
	@ResponseStatus(HttpStatus.CREATED)
	public Auditor update(@RequestBody Auditor auditor, @PathVariable String dni) {	//Como viene en formato JSON es necesario convertirlo	
		return auditorMapper.insert(auditor);
	}
	*/
}
