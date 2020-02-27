package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.auditorias.springboot.backend.model.Questionnaire;
import com.auditorias.springboot.backend.model.Questionnaire_Answers;

public interface QuestionnaireMapper {
	
	/*
	 * Create a Questionnaire
	 */
	@Insert("insert into asi_questionnaire(id_user,id_audit,id_asset) values (#{id_user},#{id_audit},#{id_asset})")
	void createQuestionnaire(Long id_user, Long id_audit, Long id_asset);
	
	/*
	 * Associate answers with questionnaire
	 */
	@Insert("insert into asi_questionnaire_answers(id_questionnaire,id_pregunta,id_respuesta,id_vulnerability,id_threat,score) values (#{id_questionnaire},#{id_pregunta},#{id_respuesta},#{id_vulnerability},#{id_threat},#{score})")
	void associateQuestions(Long id_questionnaire, Long id_pregunta, Long id_respuesta, Long id_vulnerability, Long id_threat, Long score);
	
	/*
	 * Updates Questions Answers
	 */
	@Update("update asi_questionnaire_answers set id_questionnaire=#{id_questionnaire} where id_message=#{id_message}")
	void updateMessage(Long id_questionnaire, Long id_pregunta, Long id_respuesta, Long id_vulnerability, Long id_threat, Long score);
	
	/* Search a Questionnaire */
	@Select("select * from asi_questionnaire where id_user=#{id_user} and id_audit=#{id_audit} and id_asset=#{id_asset}")
	List<Questionnaire> findQuestionnaire(Long id_user, Long id_audit, Long id_asset);
	
	/* Search a Questionnaire */
	@Select("select asi_questionnaire_answers.id_questionnaire_answers,asi_questionnaire_answers.id_questionnaire,asi_questionnaire_answers.id_pregunta,asi_questionnaire_answers.id_respuesta,asi_questionnaire_answers.id_vulnerability,asi_questionnaire_answers.id_threat,asi_questionnaire_answers.score from asi_questionnaire, asi_questionnaire_answers "
			+ "where asi_questionnaire.id_user=#{id_user} and asi_questionnaire.id_audit=#{id_audit} "
			+ "and asi_questionnaire.id_asset=#{id_asset}")
	List<Questionnaire_Answers> getAnsweredQuestionnaire(Long id_audit, Long id_user, Long id_asset);

}
