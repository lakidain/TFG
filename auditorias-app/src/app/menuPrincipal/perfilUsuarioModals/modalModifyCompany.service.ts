import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { Injectable } from '@angular/core';
import { Empresa } from '../../empresa/empresa';
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api

import { URL_BACKEND } from '../../config/config';

@Injectable({
  providedIn: 'root'
})

export class ModalModifyCompany {

  modal: boolean = false;
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlEndPoint: string = URL_BACKEND + '/api/empresa';

  constructor(private http: HttpClient) { }

  updateCompany(company: Empresa): Observable<any> {
    return this.http.put<any>(this.urlEndPoint + "/" + company.id_company, company, { headers: this.httpHeaders })
  }

  abrirModal() {
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }
}
