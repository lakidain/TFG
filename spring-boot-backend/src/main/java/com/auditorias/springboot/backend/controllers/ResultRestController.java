package com.auditorias.springboot.backend.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.auditorias.springboot.backend.dto.DtoCloseAudit;
import com.auditorias.springboot.backend.dto.DtoPdfShowResult;
import com.auditorias.springboot.backend.mapper.AuditMapper;
import com.auditorias.springboot.backend.mapper.ResultMapper;
import com.auditorias.springboot.backend.model.Audit;
import com.auditorias.springboot.backend.model.Audit_Asset;
import com.auditorias.springboot.backend.model.Empresa;
import com.auditorias.springboot.backend.model.Questionnaire_Answers;
import com.auditorias.springboot.backend.model.Usuario;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
/* PDF Creation */
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@CrossOrigin(origins = { "http://localhost:4200","*" })
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
		}
		return true;
	}

	/*
	 * Saving results on DB and generating report
	 */
	@PostMapping("/result")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createResult(@RequestBody List<DtoCloseAudit> results) throws MalformedURLException, IOException {
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
			int contador = 1;
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
			Path rutaArchivo = Paths.get("").resolve(nombreArchivo).toAbsolutePath();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo.toString() + ".pdf"));
			document.open();
			/* Portada */

			/* Adding Header */
			/*Path rutaUpaudit = Paths.get("src/main/resources").resolve("upaudit.jpg").toAbsolutePath();
			PdfContentByte canvas = writer.getDirectContentUnder();
			Image image = Image.getInstance(rutaUpaudit.toString());
			image.scaleAbsolute(312, 130);
			float x = (PageSize.A4.getWidth() - image.getScaledWidth()) / 2;
			image.setAbsolutePosition(x, 100);
			canvas.addImage(image);*/

			/* Adding index image */
			/*Path rutaIndex = Paths.get("").resolve("/index.jpg").toAbsolutePath();
			PdfContentByte canvasIndex = writer.getDirectContentUnder();
			Image imageIndex = Image.getInstance(rutaIndex.toString());
			imageIndex.scaleAbsolute(626, 507);
			float xIndex = (PageSize.A4.getWidth() - imageIndex.getScaledWidth()) / 2;
			float yIndex = (PageSize.A4.getHeight() - imageIndex.getScaledHeight()) / 2;
			imageIndex.setAbsolutePosition(xIndex, yIndex + 100);
			canvasIndex.addImage(imageIndex);*/

			//document.newPage();

			/* Pagina de Presentación */

			Font f = new Font(Font.FontFamily.HELVETICA, 30.0f, Font.BOLD, BaseColor.BLACK);
			Chunk c = new Chunk(
					"\n\nINFORME AUDITORÍA DE TIPO " + resultMapper.getResultAuditType(results.get(0).getId_audit())
							.get(0).getName_audit_type().toUpperCase() + "\n\n",
					f);
			Paragraph p1 = new Paragraph(c);
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p1);

			Font f1 = new Font(Font.FontFamily.HELVETICA, 15.0f, Font.ITALIC, BaseColor.BLACK);
			Usuario usuario = resultMapper.getResultAuditor(results.get(0).getId_audit()).get(0);
			Empresa auditora = resultMapper.getResultEmpresaAuditora(results.get(0).getId_audit()).get(0);
			Empresa auditada = resultMapper.getResultEmpresaAuditada(results.get(0).getId_audit()).get(0);
			Chunk c1 = new Chunk("EMPRESA AUDITORA:", f1);
			Chunk c2 = new Chunk("AUDITOR ASIGNADO:", f1);
			Chunk c3 = new Chunk("EMPRESA AUDITADA:", f1);
			Paragraph p2 = new Paragraph(c1);
			Paragraph p3 = new Paragraph(c2);
			Paragraph p4 = new Paragraph(c3);
			p2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			p3.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			p4.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			document.add(p2);
			document.add(new Paragraph("\n\t\t - Nombre compañia: " + auditora.getName_company() + "\n\t\t - CIF: "
					+ auditora.getCif_company() + "\n\t\t - Razón Social: " + auditora.getBusiness_name_company()
					+ "\n\t\t - Dirección: " + auditora.getAdress_company() + "\n\t\t - Dirección: "
					+ auditora.getEmail_company() + "\n\t\t - Teléfono: " + auditora.getPhone_company() + "\n\n\n"));
			document.add(p3);
			document.add(new Paragraph("\n\t\t - Nombre: " + usuario.getName_user() + "\n\t\t - DNI: "
					+ usuario.getUsername() + "\n\t\t - Email:" + usuario.getEmail_user() + "\n\t\t - Teléfono:"
					+ usuario.getPhone_user() + "\n\n\n"));
			document.add(p4);
			document.add(new Paragraph("\n\t\t - Nombre compañia: " + auditada.getName_company() + "\n\t\t - CIF: "
					+ auditada.getCif_company() + "\n\t\t - Razón Social: " + auditada.getBusiness_name_company()
					+ "\n\t\t - Dirección " + auditada.getAdress_company() + "\n\t\t - Dirección: "
					+ auditada.getEmail_company() + "\n\t\t - Teléfono: " + auditada.getPhone_company() + "\n\n"));
			document.newPage();

			/* Introduccion */
			Font fintroduccion = new Font(Font.FontFamily.HELVETICA, 20.0f, Font.BOLDITALIC, BaseColor.BLACK);
			Chunk cintroduccion = new Chunk("INTRODUCCIÓN\n\n", fintroduccion);
			Paragraph pintroduccion = new Paragraph(cintroduccion);
			pintroduccion.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(pintroduccion);
			document.add(new Paragraph(
					"La palabra auditoría tiene su origen en el verbo latino audire, que significa oír, que a su vez tiene origen en los primeros auditores que juzgaban la verdad o falsedad de algo. Nació de la necesidad del Estado de mantener controladas a las entidades que operan, por lo que se podría decir que es la acción de verificar que las circunstancias o hechos ocurran de acuerdo con lo planeado.\r\n\n"
							+ "Trasladado al ámbito de la organización es un análisis exhaustivo que realiza una persona o un grupo de personas sobre las operaciones financieras, de operación y de administración realizadas en la empresa, asegurándose que estas cumplen todas las obligaciones, tanto internas, como externas y cuyo objetivo es emitir una opinión competente acerca de los datos recogidos.\r\n\n"
							+ "El siguiente informe contiene las conclusiones obtenidas según los resultados obtenidos a través de nuestra investigación. \n\n "
							+ "La guía de puntuaciones es la siguiente: \n\n"));
			PdfPTable table = new PdfPTable(2);
			table.addCell("Nivel");
			table.addCell("Riesgo");
			table.addCell("1");
			table.addCell("Muy Bajo");
			table.addCell("2");
			table.addCell("Bajo");
			table.addCell("3");
			table.addCell("Medio");
			table.addCell("4");
			table.addCell("Alto");
			table.addCell("5");
			table.addCell("Muy Alto");
			document.add(table);
			document.newPage();

			/* Creamos el contenido del PDF */
			List<Audit_Asset> auditTypeList = resultMapper.getResultAuditAssets(
					resultMapper.getResultAuditType(results.get(0).getId_audit()).get(0).getId_audit_type());

			Font fbody = new Font(Font.FontFamily.HELVETICA, 20.0f, Font.BOLDITALIC, BaseColor.BLACK);
			Chunk cbody = new Chunk("ACTIVOS", fbody);
			Paragraph pbody = new Paragraph(cbody);
			pbody.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(pbody);

			for (int i = 0; i < auditTypeList.size(); i++) {
				/* Asset Name */
				String asset = auditTypeList.get(i).getName_audit_asset();
				fbody = new Font(Font.FontFamily.HELVETICA, 18.0f, Font.BOLDITALIC, BaseColor.BLACK);
				cbody = new Chunk(asset + "\n\n\n\n", fbody);
				pbody = new Paragraph(cbody);
				pbody.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(pbody);

				/* Puntuaciones obtenidas */
				List<DtoPdfShowResult> listaScores = resultMapper.getResults(auditTypeList.get(i).getId_audit_asset(),
						results.get(0).getId_audit());
				PdfPTable tableFinal = new PdfPTable(4);
				PdfPTable tableFinal2 = new PdfPTable(4);
				PdfPTable tableFinal3 = new PdfPTable(4);

				tableFinal.addCell("1.Disponibilidad");
				tableFinal.addCell("2.Confidencialidad");
				tableFinal.addCell("3.Integridad");
				tableFinal.addCell("4.Legalidad");
				tableFinal2.addCell("5.Amenaza");
				tableFinal2.addCell("6.Probabilidad de Amenaza");
				tableFinal2.addCell("7.Vulnerabilidad");
				tableFinal2.addCell("8.Nivel de Vulnerabilidad");
				tableFinal3.addCell("9.Probabilidad de fracaso de la seguridad");
				tableFinal3.addCell("10.Nivel de impacto");
				tableFinal3.addCell("11.Riesgo Amenaza Activo");
				tableFinal3.addCell("12.Recomendaciones");

				Float riskLevel = (float) 0;

				for (int j = 0; j < listaScores.size(); j++) {
					tableFinal.addCell(Integer.toString(listaScores.get(j).getAvailability_weight_audit_results()));
					tableFinal.addCell(Integer.toString(listaScores.get(j).getConfidentiality_weight_audit_results()));
					tableFinal.addCell(Integer.toString(listaScores.get(j).getIntegrity_weight_audit_results()));
					tableFinal.addCell(Integer.toString(listaScores.get(j).getLegality_weight_audit_results()));
					tableFinal2.addCell(listaScores.get(j).getName_audit_threat());
					tableFinal2
							.addCell(Integer.toString(listaScores.get(j).getAudit_threat_probability_audit_results()));
					tableFinal2.addCell(listaScores.get(j).getName_audit_vulnerability());
					tableFinal2.addCell(
							Float.toString(listaScores.get(j).getAudit_vulnerability_probability_audit_results()));
					tableFinal3.addCell(
							Float.toString(listaScores.get(j).getSecurity_probability_failure_audit_results()));
					tableFinal3.addCell(Integer.toString(listaScores.get(j).getImpact_level_audit_results()));
					tableFinal3.addCell(Float.toString(listaScores.get(j).getAsset_risk_audit_results()));
					tableFinal3.addCell(listaScores.get(j).getRecomendation_audit_results());

					riskLevel = riskLevel + listaScores.get(j).getAsset_risk_audit_results();
				}

				document.add(tableFinal);
				document.newPage();
				document.add(tableFinal2);
				document.newPage();
				document.add(tableFinal3);
				riskLevel = riskLevel / listaScores.size();
				Font frisk = null;
				Font risk = new Font(Font.FontFamily.HELVETICA, 13.0f, Font.BOLD, BaseColor.BLACK);
				if (riskLevel < 3) {
					frisk = new Font(Font.FontFamily.HELVETICA, 13.0f, Font.BOLD, BaseColor.GREEN);
				} else if (riskLevel >= 3 && riskLevel < 4) {
					frisk = new Font(Font.FontFamily.HELVETICA, 13.0f, Font.BOLD, BaseColor.ORANGE);
				} else if (riskLevel >= 4) {
					frisk = new Font(Font.FontFamily.HELVETICA, 13.0f, Font.BOLD, BaseColor.RED);
				}
				Chunk crisk = new Chunk("Riesgo del Activo: ", risk);
				Chunk crisk2 = new Chunk(Float.toString(riskLevel), frisk);
				Paragraph prisk = new Paragraph(crisk);
				Paragraph prisk2 = new Paragraph(crisk2);
				prisk.setAlignment(Paragraph.ALIGN_RIGHT);
				prisk2.setAlignment(Paragraph.ALIGN_RIGHT);
				document.add(prisk);
				document.add(prisk2);

				document.newPage();
			}

			/* Creamos el pie de pagina con las fechas */
			Audit auditoria = resultMapper.getResultAudit(results.get(0).getId_audit()).get(0);
			Font ffechas = new Font(Font.FontFamily.HELVETICA, 20.0f, Font.BOLDITALIC, BaseColor.BLACK);
			Chunk cfechas = new Chunk("FECHA DEL INFORME\n\n", ffechas);
			Paragraph pfechas = new Paragraph(cfechas);
			pfechas.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(pfechas);
			Paragraph inicioInforme = new Paragraph(
					"FECHA INICIO: " + new SimpleDateFormat("dd-MM-yyyy").format(auditoria.getDate_start_audit()));
			inicioInforme.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(inicioInforme);
			Paragraph finInforme = new Paragraph(
					"FECHA FIN: " + new SimpleDateFormat("dd-MM-yyyy").format(auditoria.getDate_end_audit()));
			finInforme.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(finInforme);

			document.close();
			writer.close();

			/* Subimos a AWS el fichero */
			AWSCredentials credentials = new BasicAWSCredentials("Access Key","Secret Access Key");
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("eu-west-3").withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

		    String bucketPath = "upaudit/pdf";
		    InputStream is = new FileInputStream(rutaArchivo.toString() + ".pdf");
		    ObjectMetadata meta = new ObjectMetadata();
		    meta.setContentLength(is.available());
		    s3Client.putObject(new PutObjectRequest(bucketPath,nombreArchivo+".pdf", is, meta).withCannedAcl(CannedAccessControlList.PublicRead));
			
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
