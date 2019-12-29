package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Audit_Type;

@Mapper
public interface AuditMapper {
	
	
	@Select("select * from asi_audit_type")
	List<Audit_Type> findAuditTypes();

}
