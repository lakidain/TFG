package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.dto.DtoQuestionnaireAnswers;
import com.auditorias.springboot.backend.mapper.QuestionnaireMapper;
import com.auditorias.springboot.backend.model.Questionnaire_Answers;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class QuestionnaireRestController {

	private QuestionnaireMapper questionnaireMapper;

	public QuestionnaireRestController(QuestionnaireMapper questionnaireMapper) {
		this.questionnaireMapper = questionnaireMapper;
	}

	/*
	 * Returns a List with the Questions Answered by a person for a concrete
	 * Questionnaire
	 */
	@GetMapping("/questionnaire/{id_audit}/{id_user}/{id_asset}")
	public List<Questionnaire_Answers> getAnsweredQuestionnaire(@PathVariable Long id_audit, @PathVariable Long id_user,
			@PathVariable Long id_asset) {
		return questionnaireMapper.getAnsweredQuestionnaire(id_audit, id_user, id_asset);
	}

	/*
	 * Creation of a Question with Answers
	 */
	@PostMapping("/questionnaire")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createThreat(@RequestBody List<DtoQuestionnaireAnswers> answers) {
		/* En primer lugar creamos el cuestionario */
		questionnaireMapper.createQuestionnaire(answers.get(0).getId_user(), answers.get(0).getId_audit(),
				answers.get(0).getId_asset());

		Long questionnaire_id = questionnaireMapper.findQuestionnaire(answers.get(0).getId_user(),
				answers.get(0).getId_audit(), answers.get(0).getId_asset()).get(0).getId_questionnaire();

		/* A continuacion asociamos las respuestas con el cuestionario */
		for (int i = 0; i < answers.size(); i++) {
			questionnaireMapper.associateQuestions(questionnaire_id, answers.get(i).getId_audit_question(),
					answers.get(i).getId_audit_answer(), answers.get(i).getId_audit_vulnerability(),
					answers.get(i).getId_audit_threat(), answers.get(i).getScore_audit_question_answer());
		}

		return true;
	}

	/* Modify a Questionnaire */
	@PutMapping("/questionnaire")
	public boolean updateQuestionnaire(@RequestBody List<DtoQuestionnaireAnswers> answers) {
		Long questionnaire_id = questionnaireMapper.findQuestionnaire(answers.get(0).getId_user(),
				answers.get(0).getId_audit(), answers.get(0).getId_asset()).get(0).getId_questionnaire();

		for (int i = 0; i < answers.size(); i++) {
			Long questionnaire_answer_id = questionnaireMapper
					.findAnswer(questionnaire_id, answers.get(i).getId_audit_question()).get(0)
					.getId_questionnaire_answers();
			questionnaireMapper.updateAnswers(questionnaire_answer_id, questionnaire_id,
					answers.get(i).getId_audit_question(), answers.get(i).getId_audit_answer(),
					answers.get(i).getId_audit_vulnerability(), answers.get(i).getId_audit_threat(),
					answers.get(i).getScore_audit_question_answer());
		}
		return true;
	}

	/* Check Questionnaire Credentials */
	@GetMapping("/questionnaireCredentials/{id_audit}/{id_user}") // Para generar el endpoint
	public boolean checkAppointmentCredentials(@PathVariable Long id_audit, @PathVariable Long id_user) {
		if (questionnaireMapper.checkQuestionnaireCredentials(id_audit, id_user).get(0)
				.getQuestionnaire_permit_audit_employees() == 1) {
			return true;
		} else {
			return false;
		}
	}

}
