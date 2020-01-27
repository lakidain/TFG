package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Audit;
import com.auditorias.springboot.backend.model.Audit_Type;

@Mapper
public interface AuditMapper {
	
	/* 
	 * Search a List of the Audits assigned to a worker
	 */
	@Select("select * from asi_audit where id_user_manager=#{id}")
	List<Audit> getAuditsAssigned(Long id);
	
	/* 
	 * Returns name_type from a audit_type with the id
	 */
	@Select("select * from asi_audit_types where id_audit_type=#{id}")
	List<Audit_Type> getNameType(Long id);
	
	/*
	 * Select all audit types available
	 */
	@Select("select * from asi_audit_types")
	List<Audit_Type> findAuditTypes();
	
	/*
	 * Create an audit
	 */
	@Insert("insert into asi_audit(id_user_manager,id_company_audited, id_company_auditing,id_audit_type,date_start_audit,date_end_audit) values (#{id_user_manager},#{id_company_audited},#{id_company_auditing},#{id_audit_type},#{date_start_audit},#{date_end_audit})")
	void insert(Audit audit);

}
