package com.auditorias.springboot.backend.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Audit_Answer {
	
	Long id_audit_answer;
	
	@NotNull
	@Size(min=1, max = 100)
	String answer_audit_answer;
	
	public Long getId_audit_answer() {
		return id_audit_answer;
	}
	public void setId_audit_answer(Long id_audit_answer) {
		this.id_audit_answer = id_audit_answer;
	}
	public String getAnswer_audit_answer() {
		return answer_audit_answer;
	}
	public void setAnswer_audit_answer(String answer_audit_answer) {
		this.answer_audit_answer = answer_audit_answer;
	}
	
}
