import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { Injectable } from '@angular/core';
import { AuditQuestion } from '../../../AuditQuestion';
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api

import { URL_BACKEND } from '../../../../config/config';

@Injectable({
  providedIn: 'root'
})

export class ModalModifyAuditQuestion {

  modal: boolean = false;
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlEndPoint: string = URL_BACKEND + '/api/questions';

  constructor(private http: HttpClient) { }

  abrirModal() {
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }

  updateAuditQuestion(auditQuestion: AuditQuestion): Observable<any> {
    return this.http.put<any>(this.urlEndPoint + "/" + auditQuestion.id_audit_question, auditQuestion, { headers: this.httpHeaders })
  }
}
