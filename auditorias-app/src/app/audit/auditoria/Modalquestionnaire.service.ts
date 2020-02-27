import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
import { AuditQuestion } from '../auditQuestion';
import { DtoQuestionnaire } from '../../dto/dtoQuestionnaire';

@Injectable({
  providedIn: 'root'
})
export class ModalQuestionnaire {

  modal: boolean = false;

  /* urlEndPoints*/
  private urlEndPointQuestionsList: string = 'http://localhost:8080/api/questions';
  private urlEndPointAnswersList: string = 'http://localhost:8080/api/answers';
  private urlEndPointQuestionnaire: string = 'http://localhost:8080/api/questionnaire';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  abrirModal() {
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }

  /* Get Questions List */
  getQuestions(id_audit_asset: number): Observable<AuditQuestion[]> {
    return this.http.get<AuditQuestion[]>(this.urlEndPointQuestionsList + "/" + id_audit_asset);
  }

  /* Get Answers List */
  getAnswers(id_audit_asset: number): Observable<DtoQuestionnaire[]> {
    return this.http.get<DtoQuestionnaire[]>(this.urlEndPointAnswersList + "/" + id_audit_asset);
  }

  postAnswers(answers: any): Observable<any> {
    return this.http.post<any>(this.urlEndPointQuestionnaire, answers, { headers: this.httpHeaders });
  }

  getAnsweredQuestionnaire(id_audit: number, id_user: number, id_asset: number): Observable<any> {
    return this.http.get<any>(this.urlEndPointQuestionnaire + "/" + id_audit + "/" + id_user + "/" + id_asset);
  }

  updateAnswers(answers: any): Observable<any> {
    return this.http.put<any>(this.urlEndPointQuestionnaire,  answers, { headers: this.httpHeaders });
  }
}
