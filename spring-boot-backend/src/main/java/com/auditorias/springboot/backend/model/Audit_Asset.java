package com.auditorias.springboot.backend.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Audit_Asset {

	Long id_audit_asset;
	
	@NotNull
	@Size(min=1, max = 100)
	String name_audit_asset;
	
	public Long getId_audit_asset() {
		return id_audit_asset;
	}
	public void setId_audit_asset(Long id_audit_asset) {
		this.id_audit_asset = id_audit_asset;
	}
	public String getName_audit_asset() {
		return name_audit_asset;
	}
	public void setName_audit_asset(String name_audit_asset) {
		this.name_audit_asset = name_audit_asset;
	}
	
}
