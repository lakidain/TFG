package com.auditorias.springboot.backend.model;

public class Empresa {
	
	private String company_name;
	private Long company_boss;
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public Long getCompany_boss() {
		return company_boss;
	}
	public void setCompany_boss(Long company_boss) {
		this.company_boss = company_boss;
	}
}
