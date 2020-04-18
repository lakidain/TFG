package com.auditorias.springboot.backend.model;

/**
 * Entity Role
 */
public class Role {

	private Long id_rol;
	private String name;

	/**
	 * Get parameter id_rol
	 */
	public Long getId_rol() {
		return id_rol;
	}

	/**
	 * Set parameter id_rol
	 */
	public void setId_rol(Long id_rol) {
		this.id_rol = id_rol;
	}

	/**
	 * Get parameter name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set parameter name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
