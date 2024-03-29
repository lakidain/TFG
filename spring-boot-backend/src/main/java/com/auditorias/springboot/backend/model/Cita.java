package com.auditorias.springboot.backend.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity Cita
 */
public class Cita {

	private Long id_appointment;

	@NotNull
	@Min(1)
	private Long id_audit;

	@NotNull
	@Size(min = 1, max = 100)
	private String name_appointment;

	@NotNull
	@Size(min = 1, max = 2000)
	private String description_appointment;

	@NotNull
	private Date date_appointment;

	private int state_appointment;

	/**
	 * Get parameter id_appointment
	 */
	public Long getId_appointment() {
		return id_appointment;
	}

	/**
	 * Set parameter id_appointment
	 */
	public void setId_appointment(Long id_appointment) {
		this.id_appointment = id_appointment;
	}

	/**
	 * Get parameter id_audit
	 */
	public Long getId_audit() {
		return id_audit;
	}

	/**
	 * Set parameter id_audit
	 */
	public void setId_audit(Long id_audit) {
		this.id_audit = id_audit;
	}

	/**
	 * Get parameter name_appointment
	 */
	public String getName_appointment() {
		return name_appointment;
	}

	/**
	 * Set parameter name_appointment
	 */
	public void setName_appointment(String name_appointment) {
		this.name_appointment = name_appointment;
	}

	/**
	 * Get parameter description_appointment
	 */
	public String getDescription_appointment() {
		return description_appointment;
	}

	/**
	 * Set parameter description_appointment
	 */
	public void setDescription_appointment(String description_appointment) {
		this.description_appointment = description_appointment;
	}

	/**
	 * Get parameter date_appointment
	 */
	public Date getDate_appointment() {
		return date_appointment;
	}

	/**
	 * Set parameter date_appointment
	 */
	public void setDate_appointment(Date date_appointment) {
		this.date_appointment = date_appointment;
	}

	/**
	 * Get parameter state_appointment
	 */
	public int getState_appointment() {
		return state_appointment;
	}

	/**
	 * Set parameter state_appointment
	 */
	public void setState_appointment(int state_appointment) {
		this.state_appointment = state_appointment;
	}

}
