import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
import { Injectable } from '@angular/core';

import { AuditAsset } from '../auditAsset';
import { AuditType } from '../auditType';
import { AuditThreat } from '../auditThreat';
import { AuditVulnerability } from '../auditVulnerability';
import { AuditAnswer } from '../auditAnswer';
import { AuditQuestion } from '../auditQuestion';
import { DtoAssetCreation } from '../../dto/dtoAssetCreation';

@Injectable()
export class GestionPreguntasService {

  private urlEndPointAuditAssets: string = 'http://localhost:8080/api/assets';
  private urlEndPointThreatsRelatedToAsset: string = 'http://localhost:8080/api/assetThreats';
  private urlEndPointCreateAuditType: string = 'http://localhost:8080/api/type';
  private urlEndPointAssetsRelatedToType: string = 'http://localhost:8080/api/typeAssets';
  private urlEndPointAuditThreats: string = 'http://localhost:8080/api/threats';
  private urlEndPointAuditVulnerability: string = 'http://localhost:8080/api/vulnerabilities';
  private urlEndPointAuditQuestions: string = 'http://localhost:8080/api/questions';
  private urlEndPointAuditDeleteQuestionsRelation: string = 'http://localhost:8080/api/questionsRelation';
  private urlEndPointAuditAnswers: string = 'http://localhost:8080/api/answers';
  private urlEndPointQuestionsRelatedToThreat: string = 'http://localhost:8080/api/threatQuestions';
  private urlEndPointAnswersRelatedToQuestion: string = 'http://localhost:8080/api/questionAnswers';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  /* Returns existing assets */
  getAuditAssets(): Observable<AuditAsset[]> {
    return this.http.get<AuditAsset[]>(this.urlEndPointAuditAssets);
  }

  /* Returns Assets associated with an Audit Type */
  getAssetsRelatedToType(id_audit_type: number): Observable<AuditAsset[]> {
    return this.http.get<AuditAsset[]>(this.urlEndPointAssetsRelatedToType + "/" + id_audit_type);
  }

  /* Returns existing threats */
  getAuditThreats(): Observable<AuditThreat[]> {
    return this.http.get<AuditThreat[]>(this.urlEndPointAuditThreats);
  }

  /* Returns Threats associated with an Audit Asset*/
  updateThreatsRelatedToAsset(id_audit_asset: number): Observable<AuditThreat[]> {
    return this.http.get<AuditThreat[]>(this.urlEndPointThreatsRelatedToAsset + "/" + id_audit_asset);
  }

  updateQuestionsRelatedToThreat(id_audit_threat: number): Observable<any[]> {
    return this.http.get<any[]>(this.urlEndPointQuestionsRelatedToThreat + "/" + id_audit_threat);
  }

  /* Return a list with answers related to a question */
  updateAnswersRelatedToQuestion(id_audit_question: number): Observable<AuditAnswer[]> {
    return this.http.get<AuditAnswer[]>(this.urlEndPointAnswersRelatedToQuestion + "/" + id_audit_question);
  }

  /* Returns existing vulnerabilities */
  getAuditVulnerabilities(): Observable<AuditVulnerability[]> {
    return this.http.get<AuditVulnerability[]>(this.urlEndPointAuditVulnerability);
  }

  /* Returns existing questions */
  getAuditQuestions(): Observable<AuditQuestion[]> {
    return this.http.get<AuditQuestion[]>(this.urlEndPointAuditQuestions);
  }

  /* Returns existing answers */
  getAuditAnswers(): Observable<AuditAnswer[]> {
    return this.http.get<AuditAnswer[]>(this.urlEndPointAuditAnswers);
  }

  /* Creation Audit type */
  createAuditType(auditType: AuditType): Observable<any> {
    return this.http.post<any>(this.urlEndPointCreateAuditType, auditType, { headers: this.httpHeaders });
  }

  /* Delete Audit Type */
  deleteAuditType(auditType: AuditType): Observable<any> {
    return this.http.delete<any>(this.urlEndPointCreateAuditType + "/" + auditType.id_audit_type, { headers: this.httpHeaders });
  }

  /* Creation Audit asset */
  createAsset(dtoAssetCreation: DtoAssetCreation): Observable<any> {
    return this.http.post<any>(this.urlEndPointAuditAssets, dtoAssetCreation, { headers: this.httpHeaders });
  }

  /* Delete Audit Asset Relation */
  deleteAuditAsset(auditAsset: DtoAssetCreation): Observable<any> {
    return this.http.delete<any>(this.urlEndPointAuditAssets + "/" + auditAsset.id_audit_asset + "/" + auditAsset.id_audit_type, { headers: this.httpHeaders });
  }

  /* Creation Audit Threat */
  createThreat(newThreat: string, assetThreat, existingThreat): Observable<any> {
    let formData = new FormData();
    formData.append("newThreat", newThreat);
    formData.append("assetThreat", assetThreat); /* Al no tipar el dato podemos pasarlo como si fuera un String aunque sea un numero */
    formData.append("existingThreat", existingThreat);
    return this.http.post<any>(this.urlEndPointAuditThreats, formData);
  }

  /* Delete Audit Threat */
  deleteAuditThreat(id_audit_threat: number, id_audit_asset: number): Observable<any> {
    return this.http.delete<any>(this.urlEndPointAuditThreats + "/" + id_audit_threat + "/" + id_audit_asset, { headers: this.httpHeaders });
  }

  /* Creation Audit Question with Answers */
  createQuestion(threatVulnerability, newVulnerability: string, newQuestion: string, newFirstAnswer: string, newSecondAnswer: string, newThirdAnswer: string, newFourthAnswer: string, newFifthAnswer: string,
    existingVulnerability, existingQuestion, existingNewFirstAnswer, existingNewSecondtAnswer, existingNewThirdAnswer, existingNewFourthAnswer, existingNewFifthtAnswer): Observable<any> {
    let formData = new FormData();
    formData.append("threatVulnerability", threatVulnerability);
    formData.append("newVulnerability", newVulnerability);
    formData.append("newQuestion", newQuestion);
    formData.append("newFirstAnswer", newFirstAnswer);
    formData.append("newSecondAnswer", newSecondAnswer);
    formData.append("newThirdAnswer", newThirdAnswer);
    formData.append("newFourthAnswer", newFourthAnswer);
    formData.append("newFifthAnswer", newFifthAnswer);
    formData.append("existingVulnerability", existingVulnerability);
    formData.append("existingQuestion", existingQuestion);
    formData.append("existingNewFirstAnswer", existingNewFirstAnswer);
    formData.append("existingNewSecondAnswer", existingNewSecondtAnswer);
    formData.append("existingNewThirdAnswer", existingNewThirdAnswer);
    formData.append("existingNewFourthAnswer", existingNewFourthAnswer);
    formData.append("existingNewFifthtAnswer", existingNewFifthtAnswer);
    return this.http.post<any>(this.urlEndPointAuditQuestions, formData);
  }

  deleteQuestionRelation(id_audit_threat: number, id_audit_vulnerability: number, id_audit_question: number) {
    return this.http.delete<any>(this.urlEndPointAuditDeleteQuestionsRelation + "/" + id_audit_threat + "/" + id_audit_vulnerability + "/" + id_audit_question, { headers: this.httpHeaders });
  }

  /* Creation Audit Vulnerability */
  createVulnerability(newVulnerability: string, threatVulnerability, existingVulnerability): Observable<any> {
    let formData = new FormData();
    formData.append("newVulnerability", newVulnerability);
    formData.append("threatVulnerability", threatVulnerability); /* Al no tipar el dato podemos pasarlo como si fuera un String aunque sea un numero */
    formData.append("existingVulnerability", existingVulnerability);
    return this.http.post<any>(this.urlEndPointAuditVulnerability, formData);
  }
}
