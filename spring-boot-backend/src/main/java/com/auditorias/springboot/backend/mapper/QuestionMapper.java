package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Audit_Question;

public interface QuestionMapper {
	/* Return a list with all the created answers */
	@Select("select * from asi_audit_questions")
	List<Audit_Question> findAllQuestions();
	
	/* Select an Answer searching with a name */
	@Select("select * from asi_audit_questions where question_audit_question=#{question_audit_question}")
	List<Audit_Question> findQuestion(String question_audit_question);
	
	/*
	 * Create a question
	 */
	@Insert("insert into asi_audit_questions(question_audit_question) values (#{question_audit_question})")
	void insertQuestion(String question_audit_question);
	
	/*
	 * Associate a Vulnerability with a Question
	 */
	@Insert("insert into asi_audit_vulnerabilities_questions(id_audit_vulnerability,id_audit_question) values (#{id_audit_vulnerability},#{id_audit_question})")
	void associateVulnerabilityQuestion(Long id_audit_vulnerability, Long id_audit_question);
	
}
