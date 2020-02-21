package com.auditorias.springboot.backend.dto;

import java.util.Date;

public class DtoAuditList {
	
	private Long id_audit;
	private Long id_user_manager;
	private String name_user_manager;
	private Long id_company_auditing;
	private Long id_company_audited;
	private String name_company;
	private Long id_audit_type;
	private String name_audit_type;
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
	
	public String getName_user_manager() {
		return name_user_manager;
	}
	public void setName_user_manager(String name_user_manager) {
		this.name_user_manager = name_user_manager;
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
	public String getName_company() {
		return name_company;
	}
	public void setName_company(String name_company) {
		this.name_company = name_company;
	}
	public Long getId_audit_type() {
		return id_audit_type;
	}
	public void setId_audit_type(Long id_audit_type) {
		this.id_audit_type = id_audit_type;
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
	
	
}
