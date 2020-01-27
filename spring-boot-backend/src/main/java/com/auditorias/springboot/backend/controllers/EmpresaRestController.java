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

import com.auditorias.springboot.backend.mapper.EmpresaMapper;
import com.auditorias.springboot.backend.model.Empresa;

@CrossOrigin(origins = { "http://localhost:4200" }) // CrossOrigin es un porotocolo para comunicar peticiones que se
													// realizan al navegador, desde aqui podemos controlar todo
													// (metodos, direcciones)
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class EmpresaRestController {

	private EmpresaMapper empresaMapper;

	public EmpresaRestController(EmpresaMapper empresaMapper) {
		this.empresaMapper = empresaMapper;
	}

	@GetMapping("/empresasAuditing") // Para generar el endpoint
	public List<Empresa> getAllAuditing() {
		return empresaMapper.findAllAuditing();
	}
	
	@GetMapping("/empresasAudited") // Para generar el endpoint
	public List<Empresa> getAllAudited() {
		return empresaMapper.findAllAudited();
	}
	
	@PostMapping("/empresas" )
	@ResponseStatus(HttpStatus.CREATED)
	public boolean create(@RequestBody Empresa empresa) {	//Como viene en formato JSON es necesario convertirlo	
		empresaMapper.insert(empresa);	//Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;	
	}
}
