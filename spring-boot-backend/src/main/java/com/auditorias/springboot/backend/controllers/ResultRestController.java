package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.dto.DtoCloseAudit;
import com.auditorias.springboot.backend.mapper.ResultMapper;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class ResultRestController {
	
	private ResultMapper resultMapper;
	
	private ResultRestController(ResultMapper resultMapper) {
		this.resultMapper = resultMapper;
	}
	
	/*
	 * Returns a boolean checking if enough questionnaires has been completed
	 */
	@GetMapping("/checkAnswered/{id}") //Para generar el endpoint
	public boolean checkAnsweredQuestionnaire(@PathVariable Long id) {	
		int numAssetsAnswered = resultMapper.checkAnsweredQuestionnaire(id).size();
		int numAssets = resultMapper.checkAssetsToAudit(id).size();
		
		if(numAssetsAnswered == numAssets) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Returns a boolean checking if enough questionnaires has been completed
	 */
	@GetMapping("/prepareClose/{id}") //Para generar el endpoint
	public List <DtoCloseAudit> prepareClose(@PathVariable Long id) {		
		return resultMapper.prepareClose(id);
	}
	
	/*
	 * Saving results on DB and generating report
	 */
	@PostMapping("/result")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createResult(@RequestBody List<DtoCloseAudit> results) {
		/* En primer lugar guardamos los resultados en base de datos */

		/* Generamos el informe PDF */

		return true;
	}

}
