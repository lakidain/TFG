package com.auditorias.springboot.backend.controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
import com.auditorias.springboot.backend.mapper.AuditMapper;
import com.auditorias.springboot.backend.mapper.ResultMapper;
import com.auditorias.springboot.backend.model.Questionnaire_Answers;

/* PDF Creation */
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class ResultRestController {

	private ResultMapper resultMapper;
	private AuditMapper auditMapper;

	/* Deben sumar 10 */
	private int pesoProbabilidadAmenaza = 5;
	private int pesoNivelVulnerabilidad = 5;

	/* Deben sumar 15 */
	private int pesoProbabilidadFracasoSeguridad = 5;
	private int pesoNivelImpacto = 5;
	private int pesoRequerimientosInformación = 5;

	private ResultRestController(ResultMapper resultMapper, AuditMapper auditMapper) {
		this.resultMapper = resultMapper;
		this.auditMapper = auditMapper;
	}

	/*
	 * Returns a boolean checking if enough questionnaires has been completed
	 */
	@GetMapping("/checkAnswered/{id}") // Para generar el endpoint
	public boolean checkAnsweredQuestionnaire(@PathVariable Long id) {
		int numAssetsAnswered = resultMapper.checkAnsweredQuestionnaire(id).size();
		int numAssets = resultMapper.checkAssetsToAudit(id).size();

		if (numAssetsAnswered == numAssets) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Returns a boolean checking if enough questionnaires has been completed
	 */
	@GetMapping("/prepareClose/{id}") // Para generar el endpoint
	public List<DtoCloseAudit> prepareClose(@PathVariable Long id) {
		return resultMapper.prepareClose(id);
	}

	/*
	 * Returns a boolean checking if enough questionnaires has been completed
	 */
	@GetMapping("/resultAnswers") // Para generar el endpoint
	public boolean prepareClose(@RequestBody List<DtoCloseAudit> params) {
		for (int i = 0; i < params.size(); i++) {
			System.out.print("Saludos, tengo el id" + params.get(i).getId_audit());
		}
		return true;
	}

	/*
	 * Saving results on DB and generating report
	 */
	@PostMapping("/result")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createResult(@RequestBody List<DtoCloseAudit> results) {
		/* Calculo de todos los parametros necesarios */
		for (int i = 0; i < results.size(); i++) {
			/* Calculo de la probabilidad con las respuestas dadas en los cuestionarios */
			List<Questionnaire_Answers> lista = resultMapper.returnScores(results.get(i).getId_audit(),
					results.get(i).getId_audit_threat(), results.get(i).getId_audit_vulnerability());

			float vulnerabilityProbability = 0;
			for (int j = 0; j < lista.size(); j++) {
				vulnerabilityProbability = vulnerabilityProbability + lista.get(j).getScore();
			}
			vulnerabilityProbability = vulnerabilityProbability / lista.size();

			results.get(i).setAudit_vulnerability_probability_audit_results(vulnerabilityProbability); // Nivel de
																										// Vulnerabilidad
			float probFailure = ((((results.get(i).getAudit_threat_probability_audit_results()
					* pesoProbabilidadAmenaza)
					+ results.get(i).getAudit_vulnerability_probability_audit_results() * pesoNivelVulnerabilidad) / 5)
					/ 2);

			results.get(i).setSecurity_probability_failure_audit_results(probFailure);// Probabilidad de Fracaso de la
																						// seguridad
			/* Comprobación requerimientos */
			int contador = 0;
			int pesoRequerimientos = 0;

			if (results.get(i).getAvailability_weight_audit_results() != 0) {
				pesoRequerimientos = pesoRequerimientos + results.get(i).getAvailability_weight_audit_results();
				contador++;
			}
			if (results.get(i).getConfidentiality_weight_audit_results() != 0) {
				pesoRequerimientos = pesoRequerimientos + results.get(i).getConfidentiality_weight_audit_results();
				contador++;
			}
			if (results.get(i).getIntegrity_weight_audit_results() != 0) {
				pesoRequerimientos = pesoRequerimientos + results.get(i).getIntegrity_weight_audit_results();
				contador++;
			}
			if (results.get(i).getLegality_weight_audit_results() != 0) {
				pesoRequerimientos = pesoRequerimientos + results.get(i).getLegality_weight_audit_results();
				contador++;
			}

			pesoRequerimientos = pesoRequerimientos / contador;

			float audit_risk = ((results.get(i).getSecurity_probability_failure_audit_results()
					* pesoProbabilidadFracasoSeguridad) + (3 * pesoNivelImpacto)
					+ (pesoRequerimientos * pesoRequerimientosInformación)) / 5 / 3;
			results.get(i).setAsset_risk_audit_results(audit_risk); // Riesgo Amenaza Activo

			/* Guardamos los resultados en base de datos */
			resultMapper.saveResults(results.get(i));
		}

		/* Generamos el informe PDF */
		Document document = new Document();
		try {
			String nombreArchivo = UUID.randomUUID().toString();
			Path rutaArchivo = Paths.get("results").resolve(nombreArchivo).toAbsolutePath();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo.toString() + ".pdf"));
			document.open();
			/* Portada */
			document.add(new Paragraph("INFORME AUDITORIA" + "" + "TIPO" + ""));
			document.add(new Paragraph("\r\nREALIZADO POR UPAUDIT"));
			document.newPage();

			/* Introduccion */
			document.add(new Paragraph(
					"La palabra auditoría tiene su origen en el verbo latino audire, que significa oír, que a su vez tiene origen en los primeros auditores que juzgaban la verdad o falsedad de algo. Nació de la necesidad del Estado de mantener controladas a las entidades que operan, por lo que se podría decir que es la acción de verificar que las circunstancias o hechos ocurran de acuerdo con lo planeado.\r\n"
							+ "Trasladado al ámbito de la organización es un análisis exhaustivo que realiza una persona o un grupo de personas sobre las operaciones financieras, de operación y de administración realizadas en la empresa, asegurándose que estas cumplen todas las obligaciones, tanto internas, como externas y cuyo objetivo es emitir una opinión competente acerca de los datos recogidos\r\n"
							+ "El siguiente informe contiene las conclusiones obtenidas según los resultados llevados a cabo en nuestra investigación de la auditoría de tipo \r\n"
							+ "La empresa auditada se trata de "));

			/* Creamos el contenido del PDF */

			/* Creamos el pie de pagina */
			document.add(new Paragraph("Fecha Inicio: "));
			document.add(new Paragraph("Fecha Fin: "));

			document.close();
			writer.close();

			/* Guardamos en BD la ruta del informe PDF */
			resultMapper.saveReport(results.get(0).getId_audit(), nombreArchivo);

			/* Cerramos la auditoria */
			auditMapper.closeAudit(results.get(0).getId_audit());

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return true;
	}

}
