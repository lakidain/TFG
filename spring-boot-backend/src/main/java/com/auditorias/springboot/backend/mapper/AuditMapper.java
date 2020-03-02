package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.auditorias.springboot.backend.model.Audit;
import com.auditorias.springboot.backend.model.Audit_Type;

@Mapper
public interface AuditMapper {
	
	/* 
	 * Search a List of the Audits assigned to a worker
	 */
	@Select("select * from asi_audit where id_user_manager=#{id_user_manager}")
	List<Audit> getAuditsAssigned(Long id_user_manager);
	
	/*
	 * Search a List of the Audits assigned to a company
	 */
	@Select("select * from asi_audit where id_company_auditing=#{id_company_auditing}")
	List<Audit> getCompanyAudits(Long id_company_auditing);
	
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
	@Insert("insert into asi_audit(id_user_manager,id_company_audited, id_company_auditing,id_audit_type,date_start_audit,date_end_audit,state_audit) values (#{id_user_manager},#{id_company_audited},#{id_company_auditing},#{id_audit_type},#{date_start_audit},#{date_end_audit},0)")
	void insert(Audit audit);
	
	/*
	 * Delete Audit
	 */
	@Delete("delete from asi_audit where id_audit=#{id_audit}")
	void deleteAudit(Long id_audit);
	
	/*
	 * Updates an Audit with new info
	 */
	@Update("update asi_audit set id_user_manager=#{id_user_manager},date_start_audit=#{date_start_audit},date_end_audit=#{date_end_audit} where id_audit=#{id_audit}")
	void updateAudit(Audit audit);

}
