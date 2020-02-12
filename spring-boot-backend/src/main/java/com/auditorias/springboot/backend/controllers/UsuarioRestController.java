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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.dto.DtoPassword;
import com.auditorias.springboot.backend.dto.DtoRegistro;
import com.auditorias.springboot.backend.mapper.EmpresaMapper;
import com.auditorias.springboot.backend.mapper.UsuarioMapper;
import com.auditorias.springboot.backend.model.Usuario;

@CrossOrigin(origins = { "http://localhost:4200" }) // CrossOrigin es un porotocolo para comunicar peticiones que se
													// realizan al navegador, desde aqui podemos controlar todo
													// (metodos, direcciones)
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class UsuarioRestController {
	private UsuarioMapper usuarioMapper;
	private EmpresaMapper companyMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UsuarioRestController(UsuarioMapper usuarioMapper, EmpresaMapper companyMapper) {
		this.usuarioMapper = usuarioMapper;
		this.companyMapper = companyMapper;
	}

	/* METODOS RELACIONADOS CON OBTENER LOS EMPLEADOS SUBORDINADOS */
	@GetMapping("/empleados/{id_company}") // Para generar el endpoint
	public List<Usuario> getAll(@PathVariable Long id_company) {
		return usuarioMapper.findAllEmpleados(id_company);
	}

	/* METODOS CORRESPONDIENTES AL PANEL DE PERFIL DEL USUARIO */
	/* Update phone and email */
	@PutMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario user, @PathVariable Long id) {
		usuarioMapper.update(user);
		return usuarioMapper.findByUsername(user.getUsername()).get(0);
	}

	/* Password change request method */
	@PutMapping("/usuarioPassword/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean updatePassword(@RequestBody DtoPassword dtoPassword, @PathVariable Long id) {
		/* The method matches is used to compare the equality of both passwords */
		if (passwordEncoder.matches(dtoPassword.getOldPassword(),
				usuarioMapper.getOldPassword(id).get(0).getPassword())) {
			usuarioMapper.updatePassword(passwordEncoder.encode(dtoPassword.getNewPassword()), id);
			return true;
		} else {
			return false;
		}
	}

	/* Company change request method */
	@PutMapping("/usuarioCompany/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean updateCompany(@RequestBody Long id_company, @PathVariable Long id) {
		usuarioMapper.updateCompany(id_company, id, false);
		return true;
	}

	/* FIN */

	/* METODO CORRESPONDIENTE A LA CREACION DE USUARIO */
	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean create(@RequestBody DtoRegistro dtoRegistro) { // Como viene en formato JSON es necesario convertirlo
		Usuario usuario = new Usuario();
		usuario.setUsername(dtoRegistro.getUsername());
		usuario.setName_user(dtoRegistro.getName_user());
		usuario.setEmail_user(dtoRegistro.getEmail_user());
		usuario.setPhone_user(dtoRegistro.getPhone_user());

		String passwordBcrypt = passwordEncoder.encode(dtoRegistro.getPassword());
		usuario.setPassword(passwordBcrypt); // Encriptamos la contraseña

		usuarioMapper.insert(usuario);
		Long id = usuarioMapper.findByUsername(dtoRegistro.getUsername()).get(0).getId(); // If we need to enable the
																							// user being the boss we
																							// need the id
		usuario.setId(id);

		// Check if the company already exists, if not, we create it and the first
		// person is the boss
		if (usuarioMapper.checkCompany(dtoRegistro.getName_company()).size() == 0) {
			usuarioMapper.enableUser(usuario);
			companyMapper.insertAuditCompany(id, dtoRegistro.getName_company());
			Long id_company = companyMapper.getId(dtoRegistro.getName_company()).get(0).getId_company();
			usuarioMapper.updateCompany(id_company, id, true);
		} else {
			Long id_company = companyMapper.getId(dtoRegistro.getName_company()).get(0).getId_company();
			usuarioMapper.updateCompany(id_company, id, false);
		}

		// FALTARIA AÑADIR EL ROL
		return true;
	}

	/*
	 * METODO CORRESPONDIENTE A LA GESTION DE USUARIOS, CONCRETAMENTE A LA
	 * ACEPTACIÓN POR PARTE DEL JEFE
	 */
	@PutMapping("/usuarioEnable/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario enable(@RequestBody Usuario user, @PathVariable Long id) {
		usuarioMapper.enableUser(user);
		return usuarioMapper.findByUsername(user.getUsername()).get(0);
	}

	/* METODOS GENERADOS PARA LOS CLIENTES (PERSONAS AUDITADAS) */
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createCliente(@RequestParam("email_user") String email_user,
			@RequestParam("name_user") String name_user,
			@RequestParam("password") String password,
			@RequestParam("phone_user") String phone_user,
			@RequestParam("username") String username,
			@RequestParam("companyAudited") Long companyAudited,
			@RequestParam("id_audit") Long id_audit) {
		/* Primero creamos el usuario */
		Usuario usuario = new Usuario();
		usuario.setEmail_user(email_user);
		usuario.setName_user(name_user);
		usuario.setPassword(passwordEncoder.encode(password));
		usuario.setPhone_user(phone_user);
		usuario.setUsername(username);
		usuario.setId_company(companyAudited);
		usuarioMapper.insertCliente(usuario);
		/* A continuación lo asociamos a la auditoria */
		usuarioMapper.associateClienteAuditoria(id_audit, usuarioMapper.findByUsername(username).get(0).getId());
		return true;
	}
	
	/* METODOS RELACIONADOS CON OBTENER LOS EMPLEADOS SUBORDINADOS */
	@GetMapping("/clientes/{id_audit}") // Para generar el endpoint
	public List<Usuario> getEmployeesNotInAudit(@PathVariable Long id_audit) {
		return usuarioMapper.getEmployeesNotInAudit(id_audit);
	}
}
