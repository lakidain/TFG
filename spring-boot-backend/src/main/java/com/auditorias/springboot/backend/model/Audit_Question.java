package com.auditorias.springboot.backend.model;

/**
 * Entity Audit_Question
 */
public class Audit_Question {

	Long id_audit_question;
	String question_audit_question;

	/**
	 * Get parameter id_audit_question
	 */
	public Long getId_audit_question() {
		return id_audit_question;
	}

	/**
	 * Set parameter id_audit_question
	 */
	public void setId_audit_question(Long id_audit_question) {
		this.id_audit_question = id_audit_question;
	}

	/**
	 * Get parameter question_audit_question
	 */
	public String getQuestion_audit_question() {
		return question_audit_question;
	}

	/**
	 * Set parameter question_audit_question
	 */
	public void setQuestion_audit_question(String question_audit_question) {
		this.question_audit_question = question_audit_question;
	}

}
