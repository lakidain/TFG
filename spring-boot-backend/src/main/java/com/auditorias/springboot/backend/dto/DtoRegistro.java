package com.auditorias.springboot.backend.dto;

import com.auditorias.springboot.backend.model.Empresa;
import com.auditorias.springboot.backend.model.Usuario;

public class DtoRegistro {
	
	private String name_company;
	private String username;
	private String name_user;
	private String email_user;
	private String phone_user;
	private String password;
	public String getName_company() {
		return name_company;
	}
	public void setName_company(String name_company) {
		this.name_company = name_company;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName_user() {
		return name_user;
	}
	public void setName_user(String name_user) {
		this.name_user = name_user;
	}
	public String getEmail_user() {
		return email_user;
	}
	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}
	public String getPhone_user() {
		return phone_user;
	}
	public void setPhone_user(String phone_user) {
		this.phone_user = phone_user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
