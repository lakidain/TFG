package com.auditorias.springboot.backend.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.auditorias.springboot.backend.mapper.UsuarioMapper;
import com.auditorias.springboot.backend.model.Usuario;

@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private UsuarioMapper usuarioMapper ;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioMapper.findByUsername(authentication.getName()).get(0);
		
		Map<String, Object> info = new HashMap<>();

		info.put("id", usuario.getId());
		info.put("name", usuario.getName_user());
		info.put("email", usuario.getEmail_user());
		info.put("phone", usuario.getPhone_user());
		info.put("companyId", usuario.getId_company());
		
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
