package com.auditorias.springboot.backend.dto;

import java.util.Date;

public class DtoReport {
	
	private String name_company;
	private String name_audit_type;
	private Date date_start_audit;
	private Date date_end_audit;
	private Long id_report;
	
	public String getName_company() {
		return name_company;
	}
	public void setName_company(String name_company) {
		this.name_company = name_company;
	}
	public String getName_audit_type() {
		return name_audit_type;
	}
	public void setName_audit_type(String name_audit_type) {
		this.name_audit_type = name_audit_type;
	}
	public Date getDate_start_audit() {
		return date_start_audit;
	}
	public void setDate_start_audit(Date date_start_audit) {
		this.date_start_audit = date_start_audit;
	}
	public Date getDate_end_audit() {
		return date_end_audit;
	}
	public void setDate_end_audit(Date date_end_audit) {
		this.date_end_audit = date_end_audit;
	}
	public Long getId_report() {
		return id_report;
	}
	public void setId_report(Long id_report) {
		this.id_report = id_report;
	}
	
	
}
