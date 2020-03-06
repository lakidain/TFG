package com.auditorias.springboot.backend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.dto.DtoAuditQuestionsAnswers;
import com.auditorias.springboot.backend.dto.DtoQuestionnaire;
import com.auditorias.springboot.backend.mapper.AnswerMapper;
import com.auditorias.springboot.backend.model.Audit;
import com.auditorias.springboot.backend.model.Audit_Answer;

@CrossOrigin(origins= {"http://localhost:4200"}) //CrossOrigin es un porotocolo para comunicar peticiones que se realizan al navegador, desde aqui podemos controlar todo (metodos, direcciones)
@RestController //Como no va a tener vista
@RequestMapping("/api") //Aqui nos generara la url
public class AnswerRestController {
	
	private AnswerMapper answerMapper;

	private AnswerRestController(AnswerMapper answerMapper) {
		this.answerMapper = answerMapper;
	}
	
	/*
	 * Returns a List with all the questions
	 */
	@GetMapping("/answers") //Para generar el endpoint
	public List <Audit_Answer> getAllThreats() {	
		return answerMapper.findAllAnswers();
	}
	
	/*
	 * Returns a List with all the questions
	 */
	@GetMapping("/answers/{id}") //Para generar el endpoint
	public List <DtoQuestionnaire> getAssetAnswers(@PathVariable Long id) {	
		return answerMapper.getAssetAnswers(id).stream().distinct().collect(Collectors.toList());	//Removing duplicates
	}
	
	/*
	 * Returns a List with all the answers for a concrete Audit
	 */
	@GetMapping("/auditAnswers/{id_audit}/{id_audit_threat}/{id_audit_vulnerability}") //Para generar el endpoint
	public List <DtoAuditQuestionsAnswers> getAuditAnswers(@PathVariable Long id_audit,@PathVariable Long id_audit_threat,@PathVariable Long id_audit_vulnerability) {	
		return answerMapper.getAuditAnswers(id_audit,id_audit_threat,id_audit_vulnerability);
	}
	
	/* Returns a list with all the answers related to a question */
	@GetMapping("/questionAnswers/{id_audit_question}") //Para generar el endpoint
	public List <Audit_Answer> getAnswersRelatedToQuestion(@PathVariable Long id_audit_question) {	
		return answerMapper.getAnswersRelatedToQuestion(id_audit_question);
	}
	
	/* Modify an Answer */
	@PutMapping("/answers/{id}")
	public boolean updateAnswer(@RequestBody Audit_Answer answer, @PathVariable Long id){
		answerMapper.updateAnswer(answer);
		return true;
	}

}
