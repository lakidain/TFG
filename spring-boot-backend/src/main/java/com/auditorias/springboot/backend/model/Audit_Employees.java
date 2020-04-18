package com.auditorias.springboot.backend.model;

/**
 * Entity Audit_Employees
 */
public class Audit_Employees {
	private Long id_audit_employees;
	private Long id_audit;
	private Long id_user;
	private Long appointment_permit_audit_employees;
	private Long questionnaire_permit_audit_employees;
	private Long report_permit_audit_employees;

	/**
	 * Get parameter id_audit_employees
	 */
	public Long getId_audit_employees() {
		return id_audit_employees;
	}

	/**
	 * Set parameter id_audit_employees
	 */
	public void setId_audit_employees(Long id_audit_employees) {
		this.id_audit_employees = id_audit_employees;
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

	/**
	 * Get parameter appointment_permit_audit_employees
	 */
	public Long getAppointment_permit_audit_employees() {
		return appointment_permit_audit_employees;
	}

	/**
	 * Set parameter appointment_permit_audit_employees
	 */
	public void setAppointment_permit_audit_employees(Long appointment_permit_audit_employees) {
		this.appointment_permit_audit_employees = appointment_permit_audit_employees;
	}

	/**
	 * Get parameter questionnaire_permit_audit_employees
	 */
	public Long getQuestionnaire_permit_audit_employees() {
		return questionnaire_permit_audit_employees;
	}

	/**
	 * Set parameter questionnaire_permit_audit_employees
	 */
	public void setQuestionnaire_permit_audit_employees(Long questionnaire_permit_audit_employees) {
		this.questionnaire_permit_audit_employees = questionnaire_permit_audit_employees;
	}

	/**
	 * Get parameter report_permit_audit_employees
	 */
	public Long getReport_permit_audit_employees() {
		return report_permit_audit_employees;
	}

	/**
	 * Set parameter report_permit_audit_employees
	 */
	public void setReport_permit_audit_employees(Long report_permit_audit_employees) {
		this.report_permit_audit_employees = report_permit_audit_employees;
	}

}
