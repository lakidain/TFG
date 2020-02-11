package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Audit_Answer;

public interface AnswerMapper {
	
	/* Return a list with all the created answers */
	@Select("select * from asi_audit_answers")
	List<Audit_Answer> findAllAnswers();
	
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
