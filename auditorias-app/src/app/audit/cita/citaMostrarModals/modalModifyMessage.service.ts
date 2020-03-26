import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { Injectable } from '@angular/core';
import { Message } from '../message';
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api


import { URL_BACKEND } from '../../../config/config';

@Injectable({
  providedIn: 'root'
})
export class ModalModifyMessage {

  modal: boolean = false;
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlEndPoint: string = URL_BACKEND + '/api/message'

  constructor(private http: HttpClient) { }

  abrirModal() {
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }

  updateMessage(message: Message): Observable<any> {
    return this.http.put<any>(this.urlEndPoint + "/" + message.id_message, message, { headers: this.httpHeaders })
  }
}
