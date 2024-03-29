package com.auditorias.springboot.backend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.dto.DtoAnswersRelatedToThreat;
import com.auditorias.springboot.backend.mapper.AnswerMapper;
import com.auditorias.springboot.backend.mapper.QuestionMapper;
import com.auditorias.springboot.backend.mapper.VulnerabilityMapper;
import com.auditorias.springboot.backend.model.Audit_Question;

/**
 * API Rest controller for questions
 */
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class QuestionRestController {

	private QuestionMapper questionMapper;
	private AnswerMapper answerMapper;
	private VulnerabilityMapper vulnerabilityMapper;

	private QuestionRestController(QuestionMapper questionMapper, AnswerMapper answerMapper,
			VulnerabilityMapper vulnerabilityMapper) {
		this.questionMapper = questionMapper;
		this.answerMapper = answerMapper;
		this.vulnerabilityMapper = vulnerabilityMapper;
	}

	/**
	 * Returns a List with all the questions
	 */
	@GetMapping("/questions") // Para generar el endpoint
	public List<Audit_Question> getAllThreats() {
		return questionMapper.findAllQuestions();
	}

	/**
	 * Returns a List with all the questions for an asset related
	 */
	@GetMapping("/questions/{id}") // Para generar el endpoint
	public List<Audit_Question> getQuestionsWithAsset(@PathVariable Long id) {
		return questionMapper.getQuestionsWithAsset(id);
	}

	/**
	 * Returns a list with all the questions and vulnerabilities related to a threat
	 */
	@GetMapping("/threatQuestions/{id_audit_threat}") // Para generar el endpoint
	public List<DtoAnswersRelatedToThreat> getQuestionsRelatedToThreat(@PathVariable Long id_audit_threat) {
		return answerMapper.getQuestionsRelatedToThreat(id_audit_threat);
	}

	/**
	 * Creation of a Question with Answers
	 */
	@PostMapping("/questions")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createQuestion(@RequestParam("threatVulnerability") Long threatVulnerability,
			@RequestParam("newVulnerability") String newVulnerability, @RequestParam("newQuestion") String newQuestion,
			@RequestParam("newFirstAnswer") String newFirstAnswer,
			@RequestParam("newSecondAnswer") String newSecondAnswer,
			@RequestParam("newThirdAnswer") String newThirdAnswer,
			@RequestParam("newFourthAnswer") String newFourthAnswer,
			@RequestParam("newFifthAnswer") String newFifthAnswer,
			@RequestParam("existingVulnerability") Long existingVulnerability,
			@RequestParam("existingQuestion") Long existingQuestion,
			@RequestParam("existingNewFirstAnswer") Long existingNewFirstAnswer,
			@RequestParam("existingNewSecondAnswer") Long existingNewSecondAnswer,
			@RequestParam("existingNewThirdAnswer") Long existingNewThirdAnswer,
			@RequestParam("existingNewFourthAnswer") Long existingNewFourthAnswer,
			@RequestParam("existingNewFifthtAnswer") Long existingNewFifthtAnswer) throws Exception {

		Long createdQuestion = (long) 0;

		if (existingQuestion != 0) {
			createdQuestion = existingQuestion;
		}

		/* Para cada parametro que llega se debera crear */
		if (!("".contentEquals(newVulnerability))) { /* Just need to create the threat if it's new */
			vulnerabilityMapper.insertVulnerability(newVulnerability);
			existingVulnerability = vulnerabilityMapper.findVulnerability(newVulnerability).get(0)
					.getId_audit_vulnerability();
		}
		if (existingQuestion == 0) { /*
										 * Si la pregunta ya existe no sera necesario crear la pregunta ni las
										 * respuestas, tampoco habra que asociarlas
										 */
			if (!("".contentEquals(newQuestion))) { /* Just need to create the threat if it's new */
				questionMapper.insertQuestion(newQuestion);
				createdQuestion = questionMapper.findQuestion(newQuestion).get(0).getId_audit_question();
			}
			if (!("".contentEquals(newFirstAnswer))) { /* Just need to create the threat if it's new */
				answerMapper.insertAnswer(newFirstAnswer);
				existingNewFirstAnswer = answerMapper.findAnswer(newFirstAnswer).get(0).getId_audit_answer();
			}
			if (!("".contentEquals(newSecondAnswer))) { /* Just need to create the threat if it's new */
				answerMapper.insertAnswer(newSecondAnswer);
				existingNewSecondAnswer = answerMapper.findAnswer(newSecondAnswer).get(0).getId_audit_answer();
			}
			if (!("".contentEquals(newThirdAnswer))) { /* Just need to create the threat if it's new */
				answerMapper.insertAnswer(newThirdAnswer);
				existingNewThirdAnswer = answerMapper.findAnswer(newThirdAnswer).get(0).getId_audit_answer();
			}
			if (!("".contentEquals(newFourthAnswer))) { /* Just need to create the threat if it's new */
				answerMapper.insertAnswer(newFourthAnswer);
				existingNewFourthAnswer = answerMapper.findAnswer(newFourthAnswer).get(0).getId_audit_answer();
			}
			if (!("".contentEquals(newFifthAnswer))) { /* Just need to create the threat if it's new */
				answerMapper.insertAnswer(newFifthAnswer);
				existingNewFifthtAnswer = answerMapper.findAnswer(newFifthAnswer).get(0).getId_audit_answer();
			}
		}

		if (questionMapper
				.checkAssociationVulnerabilityQuestion(threatVulnerability, existingVulnerability, createdQuestion)
				.size() == 0) {
			questionMapper.associateVulnerabilityQuestion(threatVulnerability, existingVulnerability, createdQuestion);
		} else {
			throw new Exception("Relation already existing");
		}
		if (existingQuestion == 0) {
			answerMapper.associateQuestionAnswer(createdQuestion, existingNewFirstAnswer, 1);
			answerMapper.associateQuestionAnswer(createdQuestion, existingNewSecondAnswer, 2);
			answerMapper.associateQuestionAnswer(createdQuestion, existingNewThirdAnswer, 3);
			answerMapper.associateQuestionAnswer(createdQuestion, existingNewFourthAnswer, 4);
			answerMapper.associateQuestionAnswer(createdQuestion, existingNewFifthtAnswer, 5);
		}
		return true;
	}

	/**
	 * Modify a Question
	 */
	@PutMapping("/questions/{id}")
	public boolean updateQuestion(@Valid @RequestBody Audit_Question answer, @PathVariable Long id) {
		questionMapper.updateQuestion(answer);
		return true;
	}

	/**
	 * Delete a Question Relation
	 */
	@DeleteMapping("/questionsRelation/{id_audit_threat}/{id_audit_vulnerability}/{id_audit_question}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean deleteQuestionRelation(@PathVariable Long id_audit_threat, @PathVariable Long id_audit_vulnerability,
			@PathVariable Long id_audit_question) {
		questionMapper.deleteQuestionRelation(id_audit_threat, id_audit_vulnerability, id_audit_question);
		return true;
	}
}
