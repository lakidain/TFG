package com.auditorias.springboot.backend.model;

import java.util.Date;

public class Audit {

	private Long id_audit;
	private Long id_user_manager;
	private Long id_company_auditing;
	private Long id_company_audited;
	private Long id_audit_type;
	private Date date_start_audit;
	private Date date_end_audit;
	
	public Long getId_audit() {
		return id_audit;
	}
	public void setId_audit(Long id_audit) {
		this.id_audit = id_audit;
	}
	public Long getId_user_manager() {
		return id_user_manager;
	}
	public void setId_user_manager(Long id_user_manager) {
		this.id_user_manager = id_user_manager;
	}
	
	public Long getId_company_auditing() {
		return id_company_auditing;
	}
	public void setId_company_auditing(Long id_company_auditing) {
		this.id_company_auditing = id_company_auditing;
	}
	public Long getId_company_audited() {
		return id_company_audited;
	}
	public void setId_company_audited(Long id_company_audited) {
		this.id_company_audited = id_company_audited;
	}
	public Long getId_audit_type() {
		return id_audit_type;
	}
	public void setId_audit_type(Long id_audit_type) {
		this.id_audit_type = id_audit_type;
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
	
	
	
}
