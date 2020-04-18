package com.auditorias.springboot.backend.dto;

import javax.validation.constraints.*;

/**
 * Entity DtoRegistro
 */
public class DtoRegistro {

	@NotNull
	@Size(min = 1, max = 100)
	private String name_company;

	@NotNull
	@Pattern(regexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]$|^[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]$")
	@Size(min = 1, max = 9)
	private String username;

	@NotNull
	@Size(min = 1, max = 100)
	private String name_user;

	@NotNull
	@Email
	@Size(min = 1, max = 100)
	private String email_user;

	@NotNull
	@Pattern(regexp = "^(\\+34|0034|34)?[6|7|8|9][0-9]{8}$")
	@Size(min = 1, max = 12)
	private String phone_user;

	@NotNull
	@Size(min = 6, max = 15)
	private String password;

	/**
	 * Get parameter name_company
	 */
	public String getName_company() {
		return name_company;
	}

	/**
	 * Set parameter name_company
	 */
	public void setName_company(String name_company) {
		this.name_company = name_company;
	}

	/**
	 * Get parameter username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set parameter username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get parameter name_user
	 */
	public String getName_user() {
		return name_user;
	}

	/**
	 * Set parameter name_user
	 */
	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	/**
	 * Get parameter email_user
	 */
	public String getEmail_user() {
		return email_user;
	}

	/**
	 * Set parameter email_user
	 */
	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}

	/**
	 * Get parameter phone_user
	 */
	public String getPhone_user() {
		return phone_user;
	}

	/**
	 * Set parameter phone_user
	 */
	public void setPhone_user(String phone_user) {
		this.phone_user = phone_user;
	}

	/**
	 * Get parameter password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set parameter password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
