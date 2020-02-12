package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.mapper.AnswerMapper;
import com.auditorias.springboot.backend.mapper.QuestionMapper;
import com.auditorias.springboot.backend.model.Audit_Question;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class QuestionRestController {

	private QuestionMapper questionMapper;
	private AnswerMapper answerMapper;

	private QuestionRestController(QuestionMapper questionMapper, AnswerMapper answerMapper) {
		this.questionMapper = questionMapper;
		this.answerMapper = answerMapper;
	}
	
	/*
	 * Returns a List with all the questions
	 */
	@GetMapping("/questions") //Para generar el endpoint
	public List <Audit_Question> getAllThreats() {	
		System.out.print("Ejecutando");
		return questionMapper.findAllQuestions();
	}

	/*
	 * Creation of a Question with Answers
	 */
	@PostMapping("/questions")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createThreat(@RequestParam("newQuestion") String newQuestion,
			@RequestParam("newFirstAnswer") String newFirstAnswer,
			@RequestParam("newSecondAnswer") String newSecondAnswer,
			@RequestParam("newThirdAnswer") String newThirdAnswer,
			@RequestParam("newFourthAnswer") String newFourthAnswer,
			@RequestParam("newFifthAnswer") String newFifthAnswer,
			@RequestParam("vulnerabilityQuestion") Long vulnerabilityQuestion,
			@RequestParam("existingQuestion") Long existingQuestion,
			@RequestParam("existingNewFirstAnswer") Long existingNewFirstAnswer,
			@RequestParam("existingNewSecondAnswer") Long existingNewSecondAnswer,
			@RequestParam("existingNewThirdAnswer") Long existingNewThirdAnswer,
			@RequestParam("existingNewFourthAnswer") Long existingNewFourthAnswer,
			@RequestParam("existingNewFifthtAnswer") Long existingNewFifthtAnswer) {

		/* Para cada parametro que llega se debera crear */
		if (!("".contentEquals(newQuestion))) { /* Just need to create the threat if it's new */
			questionMapper.insertQuestion(newQuestion);
			existingQuestion = questionMapper.findQuestion(newQuestion).get(0)
					.getId_audit_question();
		}
		if (!("".contentEquals(newFirstAnswer))) { /* Just need to create the threat if it's new */
			answerMapper.insertAnswer(newFirstAnswer);
			existingNewFirstAnswer = answerMapper.findAnswer(newFirstAnswer).get(0)
					.getId_audit_answer();
		}
		if (!("".contentEquals(newSecondAnswer))) { /* Just need to create the threat if it's new */
			answerMapper.insertAnswer(newSecondAnswer);
			existingNewSecondAnswer = answerMapper.findAnswer(newSecondAnswer).get(0)
					.getId_audit_answer();
		}
		if (!("".contentEquals(newThirdAnswer))) { /* Just need to create the threat if it's new */
			answerMapper.insertAnswer(newThirdAnswer);
			existingNewThirdAnswer = answerMapper.findAnswer(newThirdAnswer).get(0)
					.getId_audit_answer();
		}
		if (!("".contentEquals(newFourthAnswer))) { /* Just need to create the threat if it's new */
			answerMapper.insertAnswer(newFourthAnswer);
			existingNewFourthAnswer = answerMapper.findAnswer(newFourthAnswer).get(0)
					.getId_audit_answer();
		}
		if (!("".contentEquals(newFifthAnswer))) { /* Just need to create the threat if it's new */
			answerMapper.insertAnswer(newFifthAnswer);
			existingNewFifthtAnswer = answerMapper.findAnswer(newFifthAnswer).get(0)
					.getId_audit_answer();
		}
		/* For each Answer we should associate it with the question */
		questionMapper.associateVulnerabilityQuestion(vulnerabilityQuestion,existingQuestion);
		answerMapper.associateQuestionAnswer(existingQuestion, existingNewFirstAnswer, 5);
		answerMapper.associateQuestionAnswer(existingQuestion, existingNewSecondAnswer, 4);
		answerMapper.associateQuestionAnswer(existingQuestion, existingNewThirdAnswer, 3);
		answerMapper.associateQuestionAnswer(existingQuestion, existingNewFourthAnswer, 2);
		answerMapper.associateQuestionAnswer(existingQuestion, existingNewFifthtAnswer, 1);
		return true;
	}
}
