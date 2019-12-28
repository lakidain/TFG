package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.mapper.UsuarioMapper;
import com.auditorias.springboot.backend.model.Auditor;
import com.auditorias.springboot.backend.model.Usuario;

@CrossOrigin(origins = { "http://localhost:4200" }) // CrossOrigin es un porotocolo para comunicar peticiones que se
													// realizan al navegador, desde aqui podemos controlar todo
													// (metodos, direcciones)
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class UsuarioRestController {
	private UsuarioMapper usuarioMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UsuarioRestController(UsuarioMapper usuarioMapper) {
		this.usuarioMapper = usuarioMapper;
	}
	
	/* METODOS RELACIONADOS CON OBTENER LOS EMPLEADOS SUBORDINADOS*/
	@GetMapping("/empleados/{companyName}") //Para generar el endpoint
	public List <Usuario> getAll(@PathVariable String companyName) {	
		return usuarioMapper.findAllEmpleados(companyName);
	}
	
	/* METODOS CORRESPONDIENTES AL PANEL DE PERFIL DEL USUARIO */
	@PutMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario user,@PathVariable Long id) {
		usuarioMapper.update(user);
		return usuarioMapper.findByUsername(user.getUsername()).get(0);
	}

	/* FIN */
	
	/* METODO CORRESPONDIENTE A LA CREACION DE USUARIO */
	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario user) { // Como viene en formato JSON es necesario convertirlo
		String passwordBcrypt = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordBcrypt); // Encriptamos la contraseña

		usuarioMapper.insert(user);
		
		// Comprobamos si ya existe la empresa, si no, el primer usuario será el jefe
		if (usuarioMapper.checkCompany(user.getName_company()).size() == 0) {
			Long id = usuarioMapper.findByUsername(user.getUsername()).get(0).getId();
			usuarioMapper.insertCompanyBoss(id, user.getName_company());
		}
		
		//FALTARIA AÑADIR EL ROL
		
		return user;
	}
	
	/* METODO CORRESPONDIENTE A LA GESTION DE USUARIOS, CONCRETAMENTE A LA ACEPTACIÓN POR PARTE DEL JEFE */
	@PutMapping("/usuarioEnable/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario enable(@RequestBody Usuario user,@PathVariable Long id) {
		usuarioMapper.enableUser(user);
		return usuarioMapper.findByUsername(user.getUsername()).get(0);
	}
}
