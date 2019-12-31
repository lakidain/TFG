package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Empresa;

@Mapper
public interface EmpresaMapper {

	@Select("select * from asi_companies")
	List<Empresa> findAll();

	@Select("select * from asi_companies where name_company=#{name_company}")
	List<Empresa> getId(String name_company);
	
	@Insert("insert into asi_companies(name_company,id_user_boss) values(#{name_company},#{id})")
	void insertCompanyBoss(Long id, String name_company);
}
