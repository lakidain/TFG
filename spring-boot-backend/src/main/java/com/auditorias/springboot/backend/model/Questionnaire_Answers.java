package com.auditorias.springboot.backend.model;

public class Questionnaire_Answers {
	
	private Long id_questionnaire_answers;
	private Long id_questionnaire;
	private Long id_pregunta;
	private Long id_respuesta;
	private Long id_vulnerability;
	private Long id_threat;
	private Long score;
	
	public Long getId_questionnaire_answers() {
		return id_questionnaire_answers;
	}
	public void setId_questionnaire_answers(Long id_questionnaire_answers) {
		this.id_questionnaire_answers = id_questionnaire_answers;
	}
	public Long getId_questionnaire() {
		return id_questionnaire;
	}
	public void setId_questionnaire(Long id_questionnaire) {
		this.id_questionnaire = id_questionnaire;
	}
	public Long getId_pregunta() {
		return id_pregunta;
	}
	public void setId_pregunta(Long id_pregunta) {
		this.id_pregunta = id_pregunta;
	}
	public Long getId_respuesta() {
		return id_respuesta;
	}
	public void setId_respuesta(Long id_respuesta) {
		this.id_respuesta = id_respuesta;
	}
	public Long getId_vulnerability() {
		return id_vulnerability;
	}
	public void setId_vulnerability(Long id_vulnerability) {
		this.id_vulnerability = id_vulnerability;
	}
	public Long getId_threat() {
		return id_threat;
	}
	public void setId_threat(Long id_threat) {
		this.id_threat = id_threat;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	
	

}
