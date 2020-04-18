package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.dto.DtoReport;

@Mapper
public interface ReportMapper {

	/**
	 * Returns a List with all the reports associated
	 */
	@Select("select asi_companies.name_company,asi_audit_types.name_audit_type,asi_audit.date_start_audit,asi_audit.date_end_audit,asi_audit_reports.route_report "
			+ "from asi_audit,asi_audit_types,asi_companies,asi_audit_reports "
			+ "where asi_audit.id_company_auditing=#{id_company_auditing} "
			+ "and asi_audit.id_audit_type=asi_audit_types.id_audit_type "
			+ "and asi_audit.id_company_audited=asi_companies.id_company "
			+ "and asi_audit.id_audit=asi_audit_reports.id_audit ")
	List<DtoReport> reportAuditorBoss(Long id_company_auditing);

	/**
	 * Returns a List with all the reports associated with AuditedBoss company
	 */
	@Select("select asi_companies.name_company,asi_audit_types.name_audit_type,asi_audit.date_start_audit,asi_audit.date_end_audit,asi_audit_reports.route_report "
			+ "from asi_audit,asi_audit_types,asi_companies,asi_audit_reports "
			+ "where asi_audit.id_company_audited=#{id_company_audited} "
			+ "and asi_audit.id_audit_type=asi_audit_types.id_audit_type "
			+ "and asi_audit.id_company_auditing=asi_companies.id_company "
			+ "and asi_audit.id_audit=asi_audit_reports.id_audit ")
	List<DtoReport> reportAuditedBoss(Long id_company_audited);

	/**
	 * Returns a List with all the reports associated with an Auditor
	 */
	@Select("select asi_companies.name_company,asi_audit_types.name_audit_type,asi_audit.date_start_audit,asi_audit.date_end_audit,asi_audit_reports.route_report "
			+ "from asi_audit,asi_audit_types,asi_companies,asi_audit_reports "
			+ "where asi_audit.id_user_manager=#{id_user_manager} "
			+ "and asi_audit.id_audit_type=asi_audit_types.id_audit_type "
			+ "and asi_audit.id_company_audited=asi_companies.id_company "
			+ "and asi_audit.id_audit=asi_audit_reports.id_audit ")
	List<DtoReport> reportAuditor(Long id_user_manager);

	/**
	 * Returns a List with all the reports associated with an employee from an
	 * Audited company
	 */
	@Select("select asi_companies.name_company,asi_audit_types.name_audit_type,asi_audit.date_start_audit,asi_audit.date_end_audit,asi_audit_reports.route_report "
			+ "from asi_audit,asi_audit_types,asi_companies,asi_audit_reports,asi_audit_employees "
			+ "where asi_audit.id_audit=asi_audit_employees.id_audit " + "and asi_audit_employees.id_user=#{id_user} "
			+ "and asi_audit_employees.report_permit_audit_employees=1 "
			+ "and asi_audit.id_audit_type=asi_audit_types.id_audit_type "
			+ "and asi_audit.id_company_auditing=asi_companies.id_company "
			+ "and asi_audit.id_audit=asi_audit_reports.id_audit ")
	List<DtoReport> reportAudited(Long id_user);
}
