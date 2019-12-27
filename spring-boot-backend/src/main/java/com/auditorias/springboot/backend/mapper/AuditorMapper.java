package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Auditor;

@Mapper
public interface AuditorMapper {
	
	@Select("select * from asi_auditors")
	List<Auditor> findAll();
	
	@Select("select * from asi_auditors where dni=#{dni}")
	List<Auditor> searchByDni(String dni);
	
	@Select("select * from asi_auditors where dni=#{dni} and password=#{password}")
	List<Auditor> authenticate(String dni, String password);
	
	@Insert("insert into auditores(name,dni,email,phone,password,companyName) values(#{name},#{dni},#{email},#{phone},#{password},#{companyName})")
	void insert(Auditor auditor);
}
