import { Injectable } from '@angular/core';
import { Empresa } from './empresa';
import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

import { URL_BACKEND } from '../config/config';

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class CompanyService {

  private urlEndPoint: string = URL_BACKEND + '/api/empresas'

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get


  create(empresa: Empresa): Observable<any> { //Lo primero es lo que recibe y lo segundo lo que retorna
    return this.http.post<any>(this.urlEndPoint, empresa, { headers: this.httpHeaders })
  }
}
