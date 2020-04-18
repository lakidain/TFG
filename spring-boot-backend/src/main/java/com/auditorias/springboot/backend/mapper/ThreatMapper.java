package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Audit_Threat;
import com.auditorias.springboot.backend.model.Usuario;

@Mapper
public interface ThreatMapper {

	/**
	 * Return all employees from a company
	 */
	@Select("select * from asi_users where id_company=#{id_company}")
	List<Usuario> findAllEmpleados(Long id_company);

	/**
	 * Returns threats related to an asset
	 */
	@Select("select asi_audit_threats.id_audit_threat, asi_audit_threats.name_audit_threat from asi_audit_assets,asi_audit_assets_threats,asi_audit_threats "
			+ "where asi_audit_assets.id_audit_asset=#{id} "
			+ "and asi_audit_assets.id_audit_asset=asi_audit_assets_threats.id_audit_asset "
			+ "and asi_audit_assets_threats.id_audit_threat=asi_audit_threats.id_audit_threat")
	List<Audit_Threat> threatsByAsset(Long id);
}
