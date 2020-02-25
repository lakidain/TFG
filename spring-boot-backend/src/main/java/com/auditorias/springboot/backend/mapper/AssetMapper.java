package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.auditorias.springboot.backend.dto.DtoAssetCreation;
import com.auditorias.springboot.backend.model.Audit_Asset;
import com.auditorias.springboot.backend.model.Audit_Threat;
import com.auditorias.springboot.backend.model.Audit_Type;
import com.auditorias.springboot.backend.model.Message;

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
	
	/* Select all assets related to an Audit Type */
	@Select("select * from asi_audit_types_assets,asi_audit_assets where id_audit_type=#{id_audit_type} "
			+ "and asi_audit_types_assets.id_audit_asset=asi_audit_assets.id_audit_asset")
	List<Audit_Asset> typeAssets(Long id_audit_type);
	
	/* Select an Asset searching with a name */
	@Select("select * from asi_audit_assets where name_audit_asset=#{name_audit_asset}")
	List<Audit_Asset> findAsset(DtoAssetCreation dtoAssetCreation);
	
	/* Select a Threat searching with a name */
	@Select("select * from asi_audit_threats where name_audit_threat=#{name_audit_threat}")
	List<Audit_Threat> findThreat(String name_audit_threat);
	
	/* Select all threats related to an Audit Asset */
	@Select("select * from asi_audit_assets_threats,asi_audit_threats where id_audit_asset=#{id_audit_asset} "
			+ "and asi_audit_assets_threats.id_audit_threat=asi_audit_threats.id_audit_threat")
	List<Audit_Threat> assetThreats(Long id_audit_asset);
	
	/*
	 * Create an audit type
	 */
	@Insert("insert into asi_audit_types(name_audit_type) values (#{name_audit_type})")
	void insertAuditType(Audit_Type audit_type);
	
	/*
	 * Updates an Audit Type
	 */
	@Update("update asi_audit_types set name_audit_type=#{name_audit_type} where id_audit_type=#{id_audit_type}")
	void updateAuditType(Audit_Type audit_type);
	
	/*
	 * Delete an audit type
	 */
	@Delete("delete from asi_audit_types where id_audit_type=#{id_audit_type}")
	void removeAuditType(Long id_audit_type);
	
	/*
	 * Create an asset
	 */
	@Insert("insert into asi_audit_assets(name_audit_asset) values (#{name_audit_asset})")
	void insertAsset(DtoAssetCreation dtoAssetCreation);
	
	/*
	 * Updates an Audit Asset
	 */
	@Update("update asi_audit_assets set name_audit_asset=#{name_audit_asset} where id_audit_asset=#{id_audit_asset}")
	void updateAuditAsset(Audit_Asset audit_asset);
	
	/*
	 * Delete an asset association
	 */
	@Delete("delete from asi_audit_types_assets where id_audit_asset=#{id_audit_asset} and id_audit_type=#{id_audit_type}")
	void deleteAsset(Long id_audit_asset,Long id_audit_type);
	
	/*
	 * Create a threat
	 */
	@Insert("insert into asi_audit_threats(name_audit_threat) values (#{name_audit_threat})")
	void insertThreat(String name_audit_threat);
	
	/*
	 * Updates an Audit Threat
	 */
	@Update("update asi_audit_threats set name_audit_threat=#{name_audit_threat} where id_audit_threat=#{id_audit_threat}")
	void updateAuditThreat(Audit_Threat audit_threat);
	
	/*
	 * Delete a threat association
	 */
	@Delete("delete from asi_audit_assets_threats where id_audit_threat=#{id_audit_threat} and id_audit_asset=#{id_audit_asset}")
	void deleteThreat(Long id_audit_threat,Long id_audit_asset);
	
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
