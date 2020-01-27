import { Injectable } from '@angular/core';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { Usuario } from '../../usuario/login/usuario';
import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class AuditoriaService {

  private urlEndPointAuditList: string = 'http://localhost:8080/api/audit'

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  /* Get Audit List */
  getAuditsAssigned(usuario: Usuario): Observable<DtoAuditList[]> {
    return this.http.get<DtoAuditList[]>(this.urlEndPointAuditList+ "/" + usuario.id);
  }
}
