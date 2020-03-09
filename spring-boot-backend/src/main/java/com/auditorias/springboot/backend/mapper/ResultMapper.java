package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.dto.DtoCloseAudit;
import com.auditorias.springboot.backend.dto.DtoPdfShowResult;
import com.auditorias.springboot.backend.model.Audit;
import com.auditorias.springboot.backend.model.Audit_Asset;
import com.auditorias.springboot.backend.model.Audit_Type;
import com.auditorias.springboot.backend.model.Empresa;
import com.auditorias.springboot.backend.model.Questionnaire;
import com.auditorias.springboot.backend.model.Questionnaire_Answers;
import com.auditorias.springboot.backend.model.Usuario;

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
			+ "order by asi_audit_threat_vulnerabilities_questions.id_audit_threat")
	List<DtoCloseAudit> prepareClose(Long id_audit);

	/* Return scores for the audit */
	@Select("select asi_questionnaire_answers.score from asi_questionnaire,asi_questionnaire_answers where asi_questionnaire.id_audit=#{id_audit} "
			+ "and asi_questionnaire.id_questionnaire=asi_questionnaire_answers.id_questionnaire "
			+ "and asi_questionnaire_answers.id_threat=#{id_threat} "
			+ "and asi_questionnaire_answers.id_vulnerability=#{id_vulnerability}")
	List<Questionnaire_Answers> returnScores(Long id_audit, Long id_threat, Long id_vulnerability);

	/* Return Audit Type name */
	@Select("select asi_audit_types.id_audit_type, asi_audit_types.name_audit_type " + "from asi_audit,asi_audit_types "
			+ "where asi_audit.id_audit=#{id_audit} " + "and asi_audit.id_audit_type=asi_audit_types.id_audit_type")
	List<Audit_Type> getResultAuditType(Long id_audit);

	/* Return Auditing name */
	@Select("select asi_companies.name_company, asi_companies.cif_company, asi_companies.business_name_company,asi_companies.adress_company,asi_companies.email_company,asi_companies.phone_company "
			+ "from asi_audit,asi_companies " + "where asi_audit.id_audit=#{id_audit} "
			+ "and asi_audit.id_company_auditing=asi_companies.id_company")
	List<Empresa> getResultEmpresaAuditora(Long id_audit);

	/* Return Audited name */
	@Select("select asi_companies.name_company, asi_companies.cif_company, asi_companies.business_name_company,asi_companies.adress_company,asi_companies.email_company,asi_companies.phone_company "
			+ "from asi_audit,asi_companies " + "where asi_audit.id_audit=#{id_audit} "
			+ "and asi_audit.id_company_audited=asi_companies.id_company")
	List<Empresa> getResultEmpresaAuditada(Long id_audit);

	/* Return Auditor manager for the Audit */
	@Select("select asi_users.name_user, asi_users.username,asi_users.email_user,asi_users.phone_user "
			+ "from asi_audit,asi_users " + "where asi_audit.id_audit=#{id_audit} "
			+ "and asi_audit.id_user_manager=asi_users.id")
	List<Usuario> getResultAuditor(Long id_audit);

	/* Return Assets related to a type */
	@Select("select asi_audit_assets.id_audit_asset, asi_audit_assets.name_audit_asset "
			+ "from asi_audit_types_assets,asi_audit_assets "
			+ "where asi_audit_types_assets.id_audit_type=#{id_audit_type} "
			+ "and asi_audit_types_assets.id_audit_asset=asi_audit_assets.id_audit_asset")
	List<Audit_Asset> getResultAuditAssets(Long id_audit_type);

	/* Return Audit */
	@Select("select * from asi_audit where id_audit=#{id_audit}")
	List<Audit> getResultAudit(Long id_audit);

	/* Return Scores related to each Asset */
	@Select("select asi_audit_results.availability_weight_audit_results,asi_audit_results.confidentiality_weight_audit_results,asi_audit_results.integrity_weight_audit_results,asi_audit_results.legality_weight_audit_results,asi_audit_results.audit_threat_probability_audit_results, "
			+ "asi_audit_results.audit_vulnerability_probability_audit_results,asi_audit_results.security_probability_failure_audit_results,asi_audit_results.impact_level_audit_results,asi_audit_results.asset_risk_audit_results,asi_audit_results.recomendation_audit_results, "
			+ "asi_audit_threats.name_audit_threat,asi_audit_vulnerabilities.name_audit_vulnerability "
			+ "from asi_audit_assets_threats,asi_audit_results,asi_audit_threats,asi_audit_vulnerabilities "
			+ "where asi_audit_assets_threats.id_audit_asset=#{id_audit_asset} "
			+ "and asi_audit_assets_threats.id_audit_threat=asi_audit_results.id_audit_threat "
			+ "and asi_audit_results.id_audit=#{id_audit} "
			+ "and asi_audit_results.id_audit_threat=asi_audit_threats.id_audit_threat "
			+ "and asi_audit_results.id_audit_vulnerability=asi_audit_vulnerabilities.id_audit_vulnerability "
			+ "order by asi_audit_results.id_audit_threat")
	List<DtoPdfShowResult> getResults(Long id_audit_asset, Long id_audit);

	/* Save Results */
	@Insert("insert into asi_audit_results(availability_weight_audit_results,confidentiality_weight_audit_results,integrity_weight_audit_results,legality_weight_audit_results,id_audit,id_audit_threat,audit_threat_probability_audit_results,id_audit_vulnerability,audit_vulnerability_probability_audit_results,security_probability_failure_audit_results,impact_level_audit_results,asset_risk_audit_results,recomendation_audit_results) "
			+ "values (#{availability_weight_audit_results},#{confidentiality_weight_audit_results},#{integrity_weight_audit_results},#{legality_weight_audit_results},#{id_audit},#{id_audit_threat},#{audit_threat_probability_audit_results},#{id_audit_vulnerability},#{audit_vulnerability_probability_audit_results},#{security_probability_failure_audit_results},#{impact_level_audit_results},#{asset_risk_audit_results},#{recomendation_audit_results})")
	void saveResults(DtoCloseAudit row);

	/* Save Report */
	@Insert("insert into asi_audit_reports(id_audit,route_report) values (#{id_audit},#{nombreArchivo})")
	void saveReport(Long id_audit, String nombreArchivo);

}
