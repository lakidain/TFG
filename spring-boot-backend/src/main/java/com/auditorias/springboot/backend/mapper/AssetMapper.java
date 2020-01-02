package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Audit_Asset;

@Mapper
public interface AssetMapper {
	
	@Select("select * from asi_audit_assets")
	List<Audit_Asset> findAll();
	
}
