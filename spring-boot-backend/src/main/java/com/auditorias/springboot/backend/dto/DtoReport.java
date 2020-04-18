package com.auditorias.springboot.backend.dto;

import java.util.Date;

/**
 * Entity DtoReport
 */
public class DtoReport {

	private String name_company;
	private String name_audit_type;
	private Date date_start_audit;
	private Date date_end_audit;
	private String route_report;

	/**
	 * Get parameter name_company
	 */
	public String getName_company() {
		return name_company;
	}

	/**
	 * Set parameter name_company
	 */
	public void setName_company(String name_company) {
		this.name_company = name_company;
	}

	/**
	 * Get parameter name_audit_type
	 */
	public String getName_audit_type() {
		return name_audit_type;
	}

	/**
	 * Set parameter name_audit_type
	 */
	public void setName_audit_type(String name_audit_type) {
		this.name_audit_type = name_audit_type;
	}

	/**
	 * Get parameter date_start_audit
	 */
	public Date getDate_start_audit() {
		return date_start_audit;
	}

	/**
	 * Set parameter date_start_audit
	 */
	public void setDate_start_audit(Date date_start_audit) {
		this.date_start_audit = date_start_audit;
	}

	/**
	 * Get parameter date_end_audit
	 */
	public Date getDate_end_audit() {
		return date_end_audit;
	}

	/**
	 * Set parameter date_end_audit
	 */
	public void setDate_end_audit(Date date_end_audit) {
		this.date_end_audit = date_end_audit;
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
