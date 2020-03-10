import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { Injectable } from '@angular/core';
import { AuditThreat } from '../../../AuditThreat';
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api

@Injectable({
  providedIn: 'root'
})

export class ModalModifyAuditThreat {

  modal: boolean = false;
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlEndPoint: string = 'http://localhost:8080/api/threats';

  constructor(private http: HttpClient) { }

  abrirModal() {
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }

  updateAuditThreat(auditThreat: AuditThreat): Observable<any> {
    return this.http.put<any>(this.urlEndPoint + "/" + auditThreat.id_audit_threat, auditThreat, { headers: this.httpHeaders })
  }
}
