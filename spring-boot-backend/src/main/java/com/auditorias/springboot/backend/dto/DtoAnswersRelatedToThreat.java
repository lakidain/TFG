package com.auditorias.springboot.backend.dto;

public class DtoAnswersRelatedToThreat {
	private Long id_audit_vulnerability;
	private String name_audit_vulnerability;
	private Long id_audit_question;
	private String question_audit_question;
	
	public Long getId_audit_vulnerability() {
		return id_audit_vulnerability;
	}
	public void setId_audit_vulnerability(Long id_audit_vulnerability) {
		this.id_audit_vulnerability = id_audit_vulnerability;
	}
	public String getName_audit_vulnerability() {
		return name_audit_vulnerability;
	}
	public void setName_audit_vulnerability(String name_audit_vulnerability) {
		this.name_audit_vulnerability = name_audit_vulnerability;
	}
	public Long getId_audit_question() {
		return id_audit_question;
	}
	public void setId_audit_question(Long id_audit_question) {
		this.id_audit_question = id_audit_question;
	}
	public String getQuestion_audit_question() {
		return question_audit_question;
	}
	public void setQuestion_audit_question(String question_audit_question) {
		this.question_audit_question = question_audit_question;
	}
	
	
}
