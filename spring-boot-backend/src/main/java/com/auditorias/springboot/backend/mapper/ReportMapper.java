package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.dto.DtoReport;
import com.auditorias.springboot.backend.model.Report;

@Mapper
public interface ReportMapper {
	
	/* Returns a List with all the reports associated */
	@Select("select asi_companies.name_company,asi_audit_types.name_audit_type,asi_audit.date_start_audit,asi_audit.date_end_audit,asi_audit_reports.id_report "
			+ "from asi_audit,asi_audit_types,asi_companies,asi_audit_reports "
			+ "where asi_audit.id_company_auditing=#{id_company_auditing} "
			+ "and asi_audit.id_audit_type=asi_audit_types.id_audit_type "
			+ "and asi_audit.id_company_audited=asi_companies.id_company "
			+ "and asi_audit.id_audit=asi_audit_reports.id_audit ")
	List<DtoReport> reportAuditorBoss(Long id_company_auditing);
	
	/* Returns report name */
	@Select("select * from asi_audit_reports where id_report=#{id_report}")
	List<Report> getReport(Long id_report);
}
