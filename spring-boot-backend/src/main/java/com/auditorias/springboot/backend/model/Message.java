package com.auditorias.springboot.backend.model;

import java.util.Date;

public class Message {
	private Long id_message;
	private Long id_appointment;
	private Long id_user;
	private String text_message;
	private Date timestamp_message;
	
	public Long getId_message() {
		return id_message;
	}
	public void setId_message(Long id_message) {
		this.id_message = id_message;
	}
	public Long getId_appointment() {
		return id_appointment;
	}
	public void setId_appointment(Long id_appointment) {
		this.id_appointment = id_appointment;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getText_message() {
		return text_message;
	}
	public void setText_message(String text_message) {
		this.text_message = text_message;
	}
	public Date getTimestamp_message() {
		return timestamp_message;
	}
	public void setTimestamp_message(Date timestamp_message) {
		this.timestamp_message = timestamp_message;
	}
	
	
}
