package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.dto.DtoCloseAudit;
import com.auditorias.springboot.backend.model.Audit_Asset;
import com.auditorias.springboot.backend.model.Questionnaire;

@Mapper
public interface ResultMapper {
	
	/* Search a Questionnaire */
	@Select("select distinct id_asset from asi_questionnaire where id_audit=#{id_audit}")
	List<Questionnaire> checkAnsweredQuestionnaire(Long id_audit);
	
	/* Number of assets to audit */
	@Select("select asi_audit_assets.id_audit_asset from asi_audit,asi_audit_types_assets, asi_audit_assets where asi_audit.id_audit=#{id_audit} "
			+ "and asi_audit.id_audit_type=asi_audit_types_assets.id_audit_type "
			+ "and asi_audit_types_assets.id_audit_asset=asi_audit_assets.id_audit_asset")
	List<Audit_Asset> checkAssetsToAudit(Long id_audit);
	
	/* Number of assets to audit */
	@Select("select distinct asi_audit_threat_vulnerabilities_questions.id_audit_threat, asi_audit_threat_vulnerabilities_questions.id_audit_vulnerability,asi_audit_threats.name_audit_threat,asi_audit_vulnerabilities.name_audit_vulnerability "
			+ "from asi_audit,asi_audit_types_assets,asi_audit_assets_threats,asi_audit_threat_vulnerabilities_questions,asi_audit_threats,asi_audit_vulnerabilities "
			+ "where asi_audit.id_audit=#{id_audit} "
			+ "and asi_audit.id_audit_type=asi_audit_types_assets.id_audit_type "
			+ "and asi_audit_types_assets.id_audit_asset=asi_audit_assets_threats.id_audit_asset "
			+ "and asi_audit_assets_threats.id_audit_threat=asi_audit_threat_vulnerabilities_questions.id_audit_threat "
			+ "and asi_audit_threat_vulnerabilities_questions.id_audit_threat=asi_audit_threats.id_audit_threat "
			+ "and asi_audit_threat_vulnerabilities_questions.id_audit_vulnerability = asi_audit_vulnerabilities.id_audit_vulnerability "
			+ "order by asi_audit_threat_vulnerabilities_questions.id_audit_vulnerability")
	List<DtoCloseAudit> prepareClose(Long id_audit);

}
