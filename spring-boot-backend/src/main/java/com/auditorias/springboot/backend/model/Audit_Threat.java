package com.auditorias.springboot.backend.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Ander
 *
 */
public class Audit_Threat {

	Long id_audit_threat;
	
	@NotNull
	@Size(min=1, max = 100)
	String name_audit_threat;
	
	public Long getId_audit_threat() {
		return id_audit_threat;
	}
	public void setId_audit_threat(Long id_audit_threat) {
		this.id_audit_threat = id_audit_threat;
	}
	public String getName_audit_threat() {
		return name_audit_threat;
	}
	public void setName_audit_threat(String name_audit_threat) {
		this.name_audit_threat = name_audit_threat;
	}
	
	
}
