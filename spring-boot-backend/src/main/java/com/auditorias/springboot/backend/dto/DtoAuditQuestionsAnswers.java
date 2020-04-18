package com.auditorias.springboot.backend.dto;

/**
 * Entity DtoAuditQuestionsAnswers
 */
public class DtoAuditQuestionsAnswers {
	private Long id_threat;
	private Long id_vulnerability;
	private String question_audit_question;
	private String answer_audit_answer;
	private int score;
	
	/**
	 * Get parameter id_threat
	 */
	public Long getId_threat() {
		return id_threat;
	}
	/**
	 * Set parameter id_threat
	 */
	public void setId_threat(Long id_threat) {
		this.id_threat = id_threat;
	}
	/**
	 * Get parameter id_vulnerability
	 */
	public Long getId_vulnerability() {
		return id_vulnerability;
	}
	/**
	 * Set parameter id_vulnerability
	 */
	public void setId_vulnerability(Long id_vulnerability) {
		this.id_vulnerability = id_vulnerability;
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
	/**
	 * Get parameter answer_audit_answer
	 */
	public String getAnswer_audit_answer() {
		return answer_audit_answer;
	}
	/**
	 * Set parameter answer_audit_answer
	 */
	public void setAnswer_audit_answer(String answer_audit_answer) {
		this.answer_audit_answer = answer_audit_answer;
	}
	/**
	 * Get parameter score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Set parameter score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
