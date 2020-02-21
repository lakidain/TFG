import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { Injectable } from '@angular/core';
import { Audit } from '../../audit/audit';
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api

@Injectable({
  providedIn: 'root'
})

export class ModalModifyAudit {

  modal: boolean = false;
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlEndPoint: string = 'http://localhost:8080/api/audit'

  constructor(private http: HttpClient) { }

  updateAudit(audit: Audit):Observable<any>{
    return this.http.put<any>(this.urlEndPoint + "/" + audit.id_audit, audit, { headers: this.httpHeaders })
  }

  abrirModal(){
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }
}
