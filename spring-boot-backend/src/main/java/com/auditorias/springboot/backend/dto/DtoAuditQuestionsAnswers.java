package com.auditorias.springboot.backend.dto;

public class DtoAuditQuestionsAnswers {
	private Long id_threat;
	private Long id_vulnerability;
	private String question_audit_question;
	private String answer_audit_answer;
	private int score;
	
	public Long getId_threat() {
		return id_threat;
	}
	public void setId_threat(Long id_threat) {
		this.id_threat = id_threat;
	}
	public Long getId_vulnerability() {
		return id_vulnerability;
	}
	public void setId_vulnerability(Long id_vulnerability) {
		this.id_vulnerability = id_vulnerability;
	}
	public String getQuestion_audit_question() {
		return question_audit_question;
	}
	public void setQuestion_audit_question(String question_audit_question) {
		this.question_audit_question = question_audit_question;
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
