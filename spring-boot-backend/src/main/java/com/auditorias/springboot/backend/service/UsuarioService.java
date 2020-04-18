package com.auditorias.springboot.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auditorias.springboot.backend.mapper.UsuarioMapper;
import com.auditorias.springboot.backend.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(UsuarioService.class);
	@Autowired
	private UsuarioMapper usuarioMapper;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioMapper.findByUsername(username).get(0);

		if (usuario == null) {
			logger.error("Error en el login: no existe el usuario" + username + "en el sistema!");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario" + username + "en el sistema!");
		}

		List<GrantedAuthority> authorities = usuarioMapper.findRole(username).stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

}
