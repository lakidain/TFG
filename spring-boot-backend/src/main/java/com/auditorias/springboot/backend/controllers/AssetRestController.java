package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.mapper.AssetMapper;
import com.auditorias.springboot.backend.model.Audit_Asset;

@CrossOrigin(origins= {"http://localhost:4200"}) //CrossOrigin es un porotocolo para comunicar peticiones que se realizan al navegador, desde aqui podemos controlar todo (metodos, direcciones)
@RestController //Como no va a tener vista
@RequestMapping("/api") //Aqui nos generara la url
public class AssetRestController {

	private AssetMapper assetMapper;
	
	public AssetRestController(AssetMapper assetMapper) {
		this.assetMapper = assetMapper;
	}
	
	/**
	 * Returns  
	 * @link      Audit_Type
	 */
	@GetMapping("/assets") //Para generar el endpoint
	public List <Audit_Asset> getAll() {	
		return assetMapper.findAll();
	}
}
