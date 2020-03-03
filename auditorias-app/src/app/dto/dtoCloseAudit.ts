export class DtoCloseAudit {
  id_audit_results: number;
  availability_weight_audit_results: number;
  confidentiality_weight_audit_results: number;
  integrity_weight_audit_results: number;
  legality_weight_audit_results: number;
  id_audit: number;
  id_audit_threat: number;
  audit_threat_probability_audit_results: number;
  id_audit_vulnerability: number;
  security_probability_failure_audit_results: number;
  impact_level_audit_results: number;
  asset_risk_audit_results: number;
}
