package com.auditorias.springboot.backend.dto;

/**
 * Entity DtoQuestionnaire
 */
public class DtoQuestionnaire {

	private Long id_audit_threat;
	private Long id_audit_vulnerability;
	private Long id_audit_question;
	private Long id_audit_answer;
	private String answer_audit_answer;
	private Long score_audit_question_answer;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer_audit_answer == null) ? 0 : answer_audit_answer.hashCode());
		result = prime * result + ((id_audit_answer == null) ? 0 : id_audit_answer.hashCode());
		result = prime * result + ((id_audit_question == null) ? 0 : id_audit_question.hashCode());
		result = prime * result + ((id_audit_threat == null) ? 0 : id_audit_threat.hashCode());
		result = prime * result + ((id_audit_vulnerability == null) ? 0 : id_audit_vulnerability.hashCode());
		result = prime * result + ((score_audit_question_answer == null) ? 0 : score_audit_question_answer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DtoQuestionnaire other = (DtoQuestionnaire) obj;
		if (answer_audit_answer == null) {
			if (other.answer_audit_answer != null)
				return false;
		} else if (!answer_audit_answer.equals(other.answer_audit_answer))
			return false;
		if (id_audit_answer == null) {
			if (other.id_audit_answer != null)
				return false;
		} else if (!id_audit_answer.equals(other.id_audit_answer))
			return false;
		if (id_audit_question == null) {
			if (other.id_audit_question != null)
				return false;
		} else if (!id_audit_question.equals(other.id_audit_question))
			return false;
		if (id_audit_threat == null) {
			if (other.id_audit_threat != null)
				return false;
		} else if (!id_audit_threat.equals(other.id_audit_threat))
			return false;
		if (id_audit_vulnerability == null) {
			if (other.id_audit_vulnerability != null)
				return false;
		} else if (!id_audit_vulnerability.equals(other.id_audit_vulnerability))
			return false;
		if (score_audit_question_answer == null) {
			if (other.score_audit_question_answer != null)
				return false;
		} else if (!score_audit_question_answer.equals(other.score_audit_question_answer))
			return false;
		return true;
	}

}
