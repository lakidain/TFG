package com.auditorias.springboot.backend.model;

public class Audit_Answer {
	
	Long id_audit_answer;
	String answer_audit_answer;
	int score;
	
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
