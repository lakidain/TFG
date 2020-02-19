import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
import { AuditThreat } from '../AuditThreat';
import { AuditVulnerability } from '../auditVulnerability';
import { AuditQuestion } from '../auditQuestion';
import { AuditAnswer } from '../auditAnswer';

@Injectable({
  providedIn: 'root'
})
export class ModalQuestionnaire {

  modal: boolean = false;

  /* urlEndPoints*/
  private urlEndPointThreatsList: string = 'http://localhost:8080/api/threats';
  private urlEndPointVulnerabilitiesList: string = 'http://localhost:8080/api/audit';
  private urlEndPointQuestionsList: string = 'http://localhost:8080/api/audit';
  private urlEndPointAnswersList: string = 'http://localhost:8080/api/audit';

  constructor(private http: HttpClient) { }

  abrirModal(){
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }

  /* Get Threats List */
  getThreats(id_audit_asset:number):Observable<AuditThreat[]>{
    return this.http.get<AuditThreat[]>(this.urlEndPointThreatsList+ "/" + id_audit_asset);
  }

  /* Get Vulnerabilities List */
  getVulnerabilities(id_audit_asset:number):Observable<AuditVulnerability[]>{
    return this.http.get<AuditVulnerability[]>(this.urlEndPointVulnerabilitiesList+ "/" + id_audit_asset);
  }

  /* Get Questions List */
  getQuestions(id_audit_asset:number):Observable<AuditQuestion[]>{
    return this.http.get<AuditQuestion[]>(this.urlEndPointQuestionsList+ "/" + id_audit_asset);
  }

  /* Get Answers List */
  getAnswers(id_audit_asset:number):Observable<AuditAnswer[]>{
    return this.http.get<AuditAnswer[]>(this.urlEndPointAnswersList+ "/" + id_audit_asset);
  }
}
