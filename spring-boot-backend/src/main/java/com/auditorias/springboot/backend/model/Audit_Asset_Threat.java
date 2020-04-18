package com.auditorias.springboot.backend.model;

/**
 * Entity Audit_Asset_Threat
 */
public class Audit_Asset_Threat {
	private Long id_audit_asset;
	private Long id_audit_threat;

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

	/**
	 * Get parameter id_audit_threat
	 */
	public Long getId_audit_threat() {
		return id_audit_threat;
	}

	/**
	 * Set parameter id_audit_threat
	 */
	public void setId_audit_threat(Long id_audit_threat) {
		this.id_audit_threat = id_audit_threat;
	}
}
