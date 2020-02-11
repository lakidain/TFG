package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.mapper.AnswerMapper;
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

}
