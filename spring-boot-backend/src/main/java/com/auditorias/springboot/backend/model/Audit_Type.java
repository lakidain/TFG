package com.auditorias.springboot.backend.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Audit_Type {
	
	Long id_audit_type;
	
	@NotNull
	@Size(min=1, max = 100)
	String name_audit_type;
	
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
	
	
	
}
