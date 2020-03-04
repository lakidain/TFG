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
	@Select("select * from asi_audit where id_user_manager=#{id_user_manager} and state_audit=0")
	List<Audit> getAuditsAssigned(Long id_user_manager);
	
	/* 
	 * Search a List of the Audits related to an employee for an audited company
	 */
	@Select("select asi_audit.id_audit,asi_audit.id_user_manager,asi_audit.id_company_auditing,asi_audit.id_company_audited,asi_audit.id_audit_type,asi_audit.date_start_audit,asi_audit.date_end_audit,asi_audit.state_audit "
			+ "from asi_audit,asi_audit_employees "
			+ "where asi_audit.id_audit=asi_audit_employees.id_audit "
			+ "and asi_audit_employees.id_user=#{id_user} "
			+ "and asi_audit.state_audit=0")
	List<Audit> getAuditsRelated(Long id_user);
	
	/*
	 * Search a List of the Audits assigned to a company
	 */
	@Select("select * from asi_audit where id_company_auditing=#{id_company_auditing} and state_audit=0;")
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
	
	/*
	 * Close Audit
	 */
	@Update("update asi_audit set state_audit=1 where id_audit=#{id_audit}")
	void closeAudit(Long id_audit);

}
