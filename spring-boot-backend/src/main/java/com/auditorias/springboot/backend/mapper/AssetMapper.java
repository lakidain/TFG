package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.dto.DtoAssetCreation;
import com.auditorias.springboot.backend.model.Audit_Asset;
import com.auditorias.springboot.backend.model.Audit_Threat;
import com.auditorias.springboot.backend.model.Audit_Type;

@Mapper
public interface AssetMapper {
	
	/* Return a list with all the created assets */
	@Select("select * from asi_audit_assets")
	List<Audit_Asset> findAllAssets();
	
	/* Return a list with all the created threaths */
	@Select("select * from asi_audit_threats")
	List<Audit_Threat> findAllThreats();
	
	/* Return a list with the Assets for the concrete Audit type*/
	@Select("select asi_audit_assets.id_audit_asset,asi_audit_assets.name_audit_asset from asi_audit,asi_audit_types_assets,asi_audit_assets where asi_audit.id_audit=#{id} and asi_audit.id_audit_type=asi_audit_types_assets.id_audit_type and asi_audit_types_assets.id_audit_asset=asi_audit_assets.id_audit_asset")
	List<Audit_Asset> auditAssets(Long id);
	
	/* Select an Asset searching with a name */
	@Select("select * from asi_audit_assets where name_audit_asset=#{name_audit_asset}")
	List<Audit_Asset> findAsset(DtoAssetCreation dtoAssetCreation);
	
	/* Select a Threat searching with a name */
	@Select("select * from asi_audit_threats where name_audit_threat=#{name_audit_threat}")
	List<Audit_Threat> findThreat(String name_audit_threat);
	
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
	 * Create a threat
	 */
	@Insert("insert into asi_audit_threats(name_audit_threat) values (#{name_audit_threat})")
	void insertThreat(String name_audit_threat);
	
	/*
	 * Associate an audit type with an audit asset
	 */
	@Insert("insert into asi_audit_types_assets(id_audit_type, id_audit_asset) values (#{id_audit_type},#{id_audit_asset})")
	void associateTypeAsset(Long id_audit_type, Long id_audit_asset);
	
	/*
	 * Associate and asset with a threat
	 */
	@Insert("insert into asi_audit_assets_threats(id_audit_asset, id_audit_threat) values (#{id_audit_asset},#{id_audit_threat})")
	void associateAssetThreat(Long id_audit_asset, Long id_audit_threat);
	
}
