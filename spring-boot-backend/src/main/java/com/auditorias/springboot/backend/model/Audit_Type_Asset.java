package com.auditorias.springboot.backend.model;

/**
 * Entity Audit_Type_Asset
 */
public class Audit_Type_Asset {

	private Long id_audit_type;
	private Long id_audit_asset;

	/**
	 * Get parameter id_audit_type
	 */
	public Long getId_audit_type() {
		return id_audit_type;
	}

	/**
	 * Set parameter id_audit_type
	 */
	public void setId_audit_type(Long id_audit_type) {
		this.id_audit_type = id_audit_type;
	}

	/**
	 * Get parameter id_audit_asset
	 */
	public Long getId_audit_asset() {
		return id_audit_asset;
	}

	/**
	 * Set parameter id_audit_asset
	 */
	public void setId_audit_asset(Long id_audit_asset) {
		this.id_audit_asset = id_audit_asset;
	}

}
