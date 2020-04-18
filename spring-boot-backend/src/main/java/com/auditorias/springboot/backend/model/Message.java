package com.auditorias.springboot.backend.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity Message
 */
public class Message {

	private Long id_message;

	@NotNull
	@Min(1)
	private Long id_appointment;

	@NotNull
	@Min(1)
	private Long id_user;

	@NotNull
	@Size(min = 1, max = 10000)
	private String text_message;

	@NotNull
	private Date timestamp_message;

	/**
	 * Get parameter id_message
	 */
	public Long getId_message() {
		return id_message;
	}

	/**
	 * Set parameter id_message
	 */
	public void setId_message(Long id_message) {
		this.id_message = id_message;
	}

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
	 * Get parameter id_user
	 */
	public Long getId_user() {
		return id_user;
	}

	/**
	 * Set parameter id_user
	 */
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	/**
	 * Get parameter text_message
	 */
	public String getText_message() {
		return text_message;
	}

	/**
	 * Set parameter text_message
	 */
	public void setText_message(String text_message) {
		this.text_message = text_message;
	}

	/**
	 * Get parameter timestamp_message
	 */
	public Date getTimestamp_message() {
		return timestamp_message;
	}

	/**
	 * Set parameter timestamp_message
	 */
	public void setTimestamp_message(Date timestamp_message) {
		this.timestamp_message = timestamp_message;
	}

}
