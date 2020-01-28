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

import com.auditorias.springboot.backend.mapper.CitaMapper;
import com.auditorias.springboot.backend.model.Cita;

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
	
	@PostMapping("/cita" )
	@ResponseStatus(HttpStatus.CREATED)
	public boolean create(@RequestBody Cita cita) {	//Como viene en formato JSON es necesario convertirlo	
		citaMapper.insert(cita);	//Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;
	}
	
	@GetMapping("/allCitas/{id}") // Para generar el endpoint
	public List<Cita> getAllCitas(@PathVariable Long id) {
		return citaMapper.getAllCitas(id);
	}

}
