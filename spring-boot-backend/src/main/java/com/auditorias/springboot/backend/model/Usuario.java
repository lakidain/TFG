package com.auditorias.springboot.backend.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Usuario {

	private Long id;
	
	@NotNull
	@Pattern(regexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]$|^[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]$")
	@Size(min=1, max = 9)
	private String username;
	
	private String password;
	
	@NotNull
	@Email
	@Size(min=1, max = 100)
	private String email_user;
	
	@NotNull
	@Pattern(regexp = "^(\\+34|0034|34)?[6|7|8|9][0-9]{8}$")
	@Size(min=1, max = 12)
	private String phone_user;
	
	private Long id_company;
	
	@NotNull
	@Size(min=1, max = 100)
	private String name_user;
	
	private Boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Long getId_company() {
		return id_company;
	}

	public void setId_company(Long id_company) {
		this.id_company = id_company;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
