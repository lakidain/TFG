import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
import { AuditQuestion } from '../auditQuestion';
import { DtoQuestionnaire } from '../../dto/dtoQuestionnaire';
import { DtoCloseAudit } from '../../dto/dtoCloseAudit';

import { URL_BACKEND } from '../../config/config';

@Injectable({
  providedIn: 'root'
})

export class ModalCloseAudit {

  modal: boolean = false;

  /* urlEndPoints*/
  private urlEndPointCheckAnsweredQuestionnaire: string = URL_BACKEND + '/api/checkAnswered';
  private urlEndPointThreatsAndVulnerabilities: string = URL_BACKEND + '/api/prepareClose';
  private urlEndPointPostResults: string = URL_BACKEND + '/api/result';
  private urlEndPointAnswers: string = URL_BACKEND + '/api/auditAnswers';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  abrirModal() {
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }

  /* Check if at least a questionnaire for each Asset has been completed */
  checkAnsweredQuestionnaire(id_audit: number): Observable<any> {
    return this.http.get<any>(this.urlEndPointCheckAnsweredQuestionnaire + "/" + id_audit);
  }

  /* Get Threats And Vulnerabilities */
  updateThreatsVulnerabilities(id_audit: number): Observable<any> {
    return this.http.get<any>(this.urlEndPointThreatsAndVulnerabilities + "/" + id_audit);
  }

  updateAnswers(threatsVulnerabilities: DtoCloseAudit): Observable<any> {
    return this.http.get<any>(this.urlEndPointAnswers + "/" + threatsVulnerabilities.id_audit + "/" + threatsVulnerabilities.id_audit_threat + "/" + threatsVulnerabilities.id_audit_vulnerability);
  }

  /* Post results and generate report */
  postResults(results: any): Observable<any> {
    return this.http.post<any>(this.urlEndPointPostResults, results, { headers: this.httpHeaders });
  }
}
