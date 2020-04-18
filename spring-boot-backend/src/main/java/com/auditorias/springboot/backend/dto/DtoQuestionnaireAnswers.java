package com.auditorias.springboot.backend.dto;

/**
 * Entity DtoQuestionnaireAnswers
 */
public class DtoQuestionnaireAnswers {

	private Long id_audit_threat;
	private Long id_audit_vulnerability;
	private Long id_audit_question;
	private Long id_audit_answer;
	private Long score_audit_question_answer;
	private Long id_asset;
	private Long id_audit;
	private Long id_user;

	/**
	 * Get parameter id_audit_threat
	 */
	public Long getId_audit_threat() {
		return id_audit_threat;
	}

	/**
	 * Set parameter id_audit_threat
	 */
	public void setId_audit_threat(Long id_audit_threat) {
		this.id_audit_threat = id_audit_threat;
	}

	/**
	 * Get parameter id_audit_vulnerability
	 */
	public Long getId_audit_vulnerability() {
		return id_audit_vulnerability;
	}

	/**
	 * Set parameter id_audit_vulnerability
	 */
	public void setId_audit_vulnerability(Long id_audit_vulnerability) {
		this.id_audit_vulnerability = id_audit_vulnerability;
	}

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
	 * Get parameter id_audit_answer
	 */
	public Long getId_audit_answer() {
		return id_audit_answer;
	}

	/**
	 * Set parameter id_audit_answer
	 */
	public void setId_audit_answer(Long id_audit_answer) {
		this.id_audit_answer = id_audit_answer;
	}

	/**
	 * Get parameter score_audit_question_answer
	 */
	public Long getScore_audit_question_answer() {
		return score_audit_question_answer;
	}

	/**
	 * Set parameter score_audit_question_answer
	 */
	public void setScore_audit_question_answer(Long score_audit_question_answer) {
		this.score_audit_question_answer = score_audit_question_answer;
	}

	/**
	 * Get parameter id_asset
	 */
	public Long getId_asset() {
		return id_asset;
	}

	/**
	 * Set parameter id_asset
	 */
	public void setId_asset(Long id_asset) {
		this.id_asset = id_asset;
	}

	/**
	 * Get parameter id_audit
	 */
	public Long getId_audit() {
		return id_audit;
	}

	/**
	 * Set parameter id_audit
	 */
	public void setId_audit(Long id_audit) {
		this.id_audit = id_audit;
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

}
