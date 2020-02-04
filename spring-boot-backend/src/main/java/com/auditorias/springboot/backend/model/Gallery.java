package com.auditorias.springboot.backend.model;

public class Gallery {
	private Long id_gallery;
	private Long id_appointment;
	private String photo_gallery;
	private String description_gallery;
	
	public Long getId_gallery() {
		return id_gallery;
	}
	public void setId_gallery(Long id_gallery) {
		this.id_gallery = id_gallery;
	}
	public Long getId_appointment() {
		return id_appointment;
	}
	public void setId_appointment(Long id_appointment) {
		this.id_appointment = id_appointment;
	}
	public String getPhoto_gallery() {
		return photo_gallery;
	}
	public void setPhoto_gallery(String photo_gallery) {
		this.photo_gallery = photo_gallery;
	}
	public String getDescription_gallery() {
		return description_gallery;
	}
	public void setDescription_gallery(String description_gallery) {
		this.description_gallery = description_gallery;
	}
	
	
}
