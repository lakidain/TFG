package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/empresas") // Para generar el endpoint
	public List<Empresa> getAll() {
		return empresaMapper.findAll();
	}
}
