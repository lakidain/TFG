export class DtoAuditList {
  id_audit: number;
  id_user_manager: number;
  name_user_manager: string;
  id_company_auditing: number;
  id_company_audited: number;
  name_company: string;
  id_audit_type: number;
  name_audit_type: string;
  date_start_audit: Date;
  date_end_audit: Date;
}
