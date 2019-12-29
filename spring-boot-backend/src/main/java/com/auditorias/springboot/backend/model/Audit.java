package com.auditorias.springboot.backend.model;

import java.util.Date;

public class Audit {

	private int id_audit;
	private int id_user_manager;
	private int id_company;
	private int id_audit_company;
	private Date date_start_audit;
	private Date date_end_audit;
	
	public int getId_audit() {
		return id_audit;
	}
	public void setId_audit(int id_audit) {
		this.id_audit = id_audit;
	}
	public int getId_user_manager() {
		return id_user_manager;
	}
	public void setId_user_manager(int id_user_manager) {
		this.id_user_manager = id_user_manager;
	}
	public int getId_company() {
		return id_company;
	}
	public void setId_company(int id_company) {
		this.id_company = id_company;
	}
	public int getId_audit_company() {
		return id_audit_company;
	}
	public void setId_audit_company(int id_audit_company) {
		this.id_audit_company = id_audit_company;
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
