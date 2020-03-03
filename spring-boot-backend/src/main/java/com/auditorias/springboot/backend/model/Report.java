package com.auditorias.springboot.backend.model;

public class Report {
	private Long id_report;
	private Long id_audit;
	private String route_report;
	
	public Long getId_report() {
		return id_report;
	}
	public void setId_report(Long id_report) {
		this.id_report = id_report;
	}
	public Long getId_audit() {
		return id_audit;
	}
	public void setId_audit(Long id_audit) {
		this.id_audit = id_audit;
	}
	public String getRoute_report() {
		return route_report;
	}
	public void setRoute_report(String route_report) {
		this.route_report = route_report;
	}
	
	
}
