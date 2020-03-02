package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.dto.DtoQuestionnaire;
import com.auditorias.springboot.backend.model.Audit_Answer;

@Mapper
public interface AnswerMapper {

	/* Return a list with all the created answers */
	@Select("select * from asi_audit_answers")
	List<Audit_Answer> findAllAnswers();

	/* Return a list with all the answers for an asset packed with aditional info */
	@Select("select asi_audit_threat_vulnerabilities_questions.id_audit_threat,asi_audit_threat_vulnerabilities_questions.id_audit_vulnerability,asi_audit_threat_vulnerabilities_questions.id_audit_question,asi_audit_answers.id_audit_answer,asi_audit_answers.answer_audit_answer,asi_audit_questions_answers.score_audit_question_answer "
			+ "from asi_audit_assets_threats,asi_audit_threat_vulnerabilities_questions,asi_audit_questions,asi_audit_questions_answers,asi_audit_answers where asi_audit_assets_threats.id_audit_asset=#{id_audit_asset} "
			+ "and asi_audit_assets_threats.id_audit_threat=asi_audit_threat_vulnerabilities_questions.id_audit_threat "
			+ "and asi_audit_threat_vulnerabilities_questions.id_audit_question=asi_audit_questions_answers.id_audit_question "
			+ "and asi_audit_questions_answers.id_audit_answer=asi_audit_answers.id_audit_answer ")
	List<DtoQuestionnaire> getAssetAnswers(Long id_audit_asset);

	/* Select a Threat searching with a name */
	@Select("select * from asi_audit_answers where answer_audit_answer=#{answer_audit_answer}")
	List<Audit_Answer> findAnswer(String answer_audit_answer);

	/*
	 * Create an answer
	 */
	@Insert("insert into asi_audit_answers(answer_audit_answer) values (#{answer_audit_answer})")
	void insertAnswer(String answer_audit_answer);

	/*
	 * Associate a Question with an answer
	 */
	@Insert("insert into asi_audit_questions_answers(id_audit_question, id_audit_answer, score_audit_question_answer) values (#{id_audit_question},#{id_audit_answer},#{score_audit_question_answer})")
	void associateQuestionAnswer(Long id_audit_question, Long id_audit_answer, int score_audit_question_answer);
}
