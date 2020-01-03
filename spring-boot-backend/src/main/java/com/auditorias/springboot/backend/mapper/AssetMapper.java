package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.dto.DtoAssetCreation;
import com.auditorias.springboot.backend.model.Audit_Asset;
import com.auditorias.springboot.backend.model.Audit_Type;

@Mapper
public interface AssetMapper {
	
	@Select("select * from asi_audit_assets")
	List<Audit_Asset> findAll();
	
	@Select("select * from asi_audit_assets where name_audit_asset=#{name_audit_asset}")
	List<Audit_Asset> findName(DtoAssetCreation dtoAssetCreation);
	
	/*
	 * Create an audit type
	 */
	@Insert("insert into asi_audit_types(name_audit_type) values (#{name_audit_type})")
	void insertAuditType(Audit_Type audit_type);
	
	/*
	 * Create an asset
	 */
	@Insert("insert into asi_audit_assets(name_audit_asset) values (#{name_audit_asset})")
	void insertAsset(DtoAssetCreation dtoAssetCreation);
	
	/*
	 * Associate an audit type with an audit asset
	 */
	@Insert("insert into asi_audit_types_assets(id_audit_type, id_audit_asset) values (#{id_audit_type},#{id_audit_asset})")
	void associateTypeAsset(Long id_audit_type, Long id_audit_asset);
	
}
