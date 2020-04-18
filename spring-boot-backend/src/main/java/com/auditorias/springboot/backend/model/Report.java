package com.auditorias.springboot.backend.model;

/**
 * Entity Report
 */
public class Report {
	private Long id_report;
	private Long id_audit;
	private String route_report;

	/**
	 * Get parameter id_report
	 */
	public Long getId_report() {
		return id_report;
	}

	/**
	 * Set parameter id_report
	 */
	public void setId_report(Long id_report) {
		this.id_report = id_report;
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
	 * Get parameter route_report
	 */
	public String getRoute_report() {
		return route_report;
	}

	/**
	 * Set parameter route_report
	 */
	public void setRoute_report(String route_report) {
		this.route_report = route_report;
	}

}
