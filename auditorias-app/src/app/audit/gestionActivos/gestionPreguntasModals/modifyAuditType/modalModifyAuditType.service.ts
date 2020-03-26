import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { Injectable } from '@angular/core';
import { AuditType } from '../../../AuditType';
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api

import { URL_BACKEND } from '../../../../config/config';

@Injectable({
  providedIn: 'root'
})

export class ModalModifyAuditType {

  modal: boolean = false;
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlEndPoint: string = URL_BACKEND + '/api/type'

  constructor(private http: HttpClient) { }

  abrirModal() {
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }

  updateAuditType(auditType: AuditType): Observable<any> {
    return this.http.put<any>(this.urlEndPoint + "/" + auditType.id_audit_type, auditType, { headers: this.httpHeaders })
  }
}
