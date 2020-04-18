package com.auditorias.springboot.backend.dto;

/**
 * Entity DtoCloseAudit
 */
public class DtoCloseAudit {

	/* Database info */
	private Long id_audit_results;
	private int availability_weight_audit_results;
	private int confidentiality_weight_audit_results;
	private int integrity_weight_audit_results;
	private int legality_weight_audit_results;
	private Long id_audit;
	private Long id_audit_threat;
	private int audit_threat_probability_audit_results;
	private Long id_audit_vulnerability;
	private float audit_vulnerability_probability_audit_results;
	private float security_probability_failure_audit_results;
	private int impact_level_audit_results;
	private float asset_risk_audit_results;
	private String recomendation_audit_results;

	/* Aditional info */
	private String name_audit_threat;
	private String name_audit_vulnerability;

	/**
	 * Get parameter recomendation_audit_results
	 */
	public String getRecomendation_audit_results() {
		return recomendation_audit_results;
	}

	/**
	 * Set parameter recomendation_audit_results
	 */
	public void setRecomendation_audit_results(String recomendation_audit_results) {
		this.recomendation_audit_results = recomendation_audit_results;
	}

	/**
	 * Get parameter audit_vulnerability_probability_audit_results
	 */
	public float getAudit_vulnerability_probability_audit_results() {
		return audit_vulnerability_probability_audit_results;
	}

	/**
	 * Set parameter audit_vulnerability_probability_audit_results
	 */
	public void setAudit_vulnerability_probability_audit_results(float audit_vulnerability_probability_audit_results) {
		this.audit_vulnerability_probability_audit_results = audit_vulnerability_probability_audit_results;
	}

	/**
	 * Get parameter id_audit_results
	 */
	public Long getId_audit_results() {
		return id_audit_results;
	}

	/**
	 * Set parameter id_audit_results
	 */
	public void setId_audit_results(Long id_audit_results) {
		this.id_audit_results = id_audit_results;
	}

	/**
	 * Get parameter availability_weight_audit_results
	 */
	public int getAvailability_weight_audit_results() {
		return availability_weight_audit_results;
	}

	/**
	 * Set parameter availability_weight_audit_results
	 */
	public void setAvailability_weight_audit_results(int availability_weight_audit_results) {
		this.availability_weight_audit_results = availability_weight_audit_results;
	}

	/**
	 * Get parameter confidentiality_weight_audit_results
	 */
	public int getConfidentiality_weight_audit_results() {
		return confidentiality_weight_audit_results;
	}

	/**
	 * Set parameter confidentiality_weight_audit_results
	 */
	public void setConfidentiality_weight_audit_results(int confidentiality_weight_audit_results) {
		this.confidentiality_weight_audit_results = confidentiality_weight_audit_results;
	}

	/**
	 * Get parameter integrity_weight_audit_results
	 */
	public int getIntegrity_weight_audit_results() {
		return integrity_weight_audit_results;
	}

	/**
	 * Set parameter integrity_weight_audit_results
	 */
	public void setIntegrity_weight_audit_results(int integrity_weight_audit_results) {
		this.integrity_weight_audit_results = integrity_weight_audit_results;
	}

	/**
	 * Get parameter legality_weight_audit_results
	 */
	public int getLegality_weight_audit_results() {
		return legality_weight_audit_results;
	}

	/**
	 * Set parameter legality_weight_audit_results
	 */
	public void setLegality_weight_audit_results(int legality_weight_audit_results) {
		this.legality_weight_audit_results = legality_weight_audit_results;
	}

	/**
	 * Get parameter id_audit
	 */
	public Long getId_audit() {
		return id_audit;
	}

	/**
	 * Set parameter id_audit
	 */
	public void setId_audit(Long id_audit) {
		this.id_audit = id_audit;
	}

	/**
	 * Get parameter audit_threat_probability_audit_results
	 */
	public int getAudit_threat_probability_audit_results() {
		return audit_threat_probability_audit_results;
	}

	/**
	 * Set parameter audit_threat_probability_audit_results
	 */
	public void setAudit_threat_probability_audit_results(int audit_threat_probability_audit_results) {
		this.audit_threat_probability_audit_results = audit_threat_probability_audit_results;
	}

	/**
	 * Get parameter security_probability_failure_audit_results
	 */
	public float getSecurity_probability_failure_audit_results() {
		return security_probability_failure_audit_results;
	}

	/**
	 * Set parameter security_probability_failure_audit_results
	 */
	public void setSecurity_probability_failure_audit_results(float security_probability_failure_audit_results) {
		this.security_probability_failure_audit_results = security_probability_failure_audit_results;
	}

	/**
	 * Get parameter impact_level_audit_results
	 */
	public int getImpact_level_audit_results() {
		return impact_level_audit_results;
	}

	/**
	 * Set parameter impact_level_audit_results
	 */
	public void setImpact_level_audit_results(int impact_level_audit_results) {
		this.impact_level_audit_results = impact_level_audit_results;
	}

	/**
	 * Get parameter asset_risk_audit_results
	 */
	public float getAsset_risk_audit_results() {
		return asset_risk_audit_results;
	}

	/**
	 * Set parameter asset_risk_audit_results
	 */
	public void setAsset_risk_audit_results(float asset_risk_audit_results) {
		this.asset_risk_audit_results = asset_risk_audit_results;
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

	/**
	 * Get parameter id_audit_vulnerability
	 */
	public Long getId_audit_vulnerability() {
		return id_audit_vulnerability;
	}

	/**
	 * Set parameter id_audit_vulnerability
	 */
	public void setId_audit_vulnerability(Long id_audit_vulnerability) {
		this.id_audit_vulnerability = id_audit_vulnerability;
	}

	/**
	 * Get parameter name_audit_threat
	 */
	public String getName_audit_threat() {
		return name_audit_threat;
	}

	/**
	 * Set parameter name_audit_threat
	 */
	public void setName_audit_threat(String name_audit_threat) {
		this.name_audit_threat = name_audit_threat;
	}

	/**
	 * Get parameter name_audit_vulnerability
	 */
	public String getName_audit_vulnerability() {
		return name_audit_vulnerability;
	}

	/**
	 * Set parameter name_audit_vulnerability
	 */
	public void setName_audit_vulnerability(String name_audit_vulnerability) {
		this.name_audit_vulnerability = name_audit_vulnerability;
	}

}
