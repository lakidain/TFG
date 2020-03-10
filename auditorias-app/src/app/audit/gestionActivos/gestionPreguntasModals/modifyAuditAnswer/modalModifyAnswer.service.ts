import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { Injectable } from '@angular/core';
import { AuditAnswer } from '../../../AuditAnswer';
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api

@Injectable({
  providedIn: 'root'
})

export class ModalModifyAuditAnswer {

  modal: boolean = false;
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlEndPoint: string = 'http://localhost:8080/api/answers';

  constructor(private http: HttpClient) { }

  abrirModal() {
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }

  updateAuditAnswer(auditAnswer: AuditAnswer): Observable<any> {
    return this.http.put<any>(this.urlEndPoint + "/" + auditAnswer.id_audit_answer, auditAnswer, { headers: this.httpHeaders })
  }
}
