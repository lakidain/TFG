package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Empresa;

@Mapper
public interface EmpresaMapper {
	
	@Select("select * from asi_companies")
	List<Empresa> findAll();
}
