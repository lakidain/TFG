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
	 * Select all audit types available
	 */
	@Select("select * from asi_audit_type")
	List<Audit_Type> findAuditTypes();
	
	/*
	 * Create an audit
	 */
	@Insert("insert into asi_audit(id_user_manager,id_company,id_audit_type,date_start_audit,date_end_audit) values (#{id_user_manager},#{id_company},#{id_audit_type},#{date_start_audit},#{date_end_audit})")
	void insert(Audit audit);

}
