package com.auditorias.springboot.backend.model;

import java.util.Date;

public class Questionnaire {
	
	private Long id_questionnaire;
	private Long id_user;
	private Long id_audit;
	private Long id_asset;
	private Date timestamp_questionnaire;
	
	public Long getId_questionnaire() {
		return id_questionnaire;
	}
	public void setId_questionnaire(Long id_questionnaire) {
		this.id_questionnaire = id_questionnaire;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public Long getId_audit() {
		return id_audit;
	}
	public void setId_audit(Long id_audit) {
		this.id_audit = id_audit;
	}
	public Long getId_asset() {
		return id_asset;
	}
	public void setId_asset(Long id_asset) {
		this.id_asset = id_asset;
	}
	public Date getTimestamp_questionnaire() {
		return timestamp_questionnaire;
	}
	public void setTimestamp_questionnaire(Date timestamp_questionnaire) {
		this.timestamp_questionnaire = timestamp_questionnaire;
	}
	
	
}