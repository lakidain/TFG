package com.auditorias.springboot.backend.dto;

/**
 * Entity DtoAnswersRelatedToThreat
 */
public class DtoAssetCreation {

	private Long id_audit_type;
	private Long id_audit_asset;
	private String name_audit_asset;

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

	/**
	 * Get parameter name_audit_asset
	 */
	public String getName_audit_asset() {
		return name_audit_asset;
	}

	/**
	 * Set parameter name_audit_asset
	 */
	public void setName_audit_asset(String name_audit_asset) {
		this.name_audit_asset = name_audit_asset;
	}

}
