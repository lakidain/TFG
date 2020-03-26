import { Injectable } from '@angular/core';
import { Empresa } from '../empresa/empresa';

import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

import { URL_BACKEND } from '../config/config';

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class PerfilUsuarioService {

  private urlEndPointEmpresas: string = URL_BACKEND + '/api/empresasAuditing';
  private urlEndPointGetCompany: string = URL_BACKEND + '/api/empresa';

  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get


  /* Metodo que devuelve las empresas posibles para auditar*/
  getEmpresas(): Observable<Empresa[]> { //Para que las peticiones sean asincronas hay que implementar observable que hace que nuestro arreglo sea un stream
    return this.http.get<Empresa[]>(this.urlEndPointEmpresas); //El servicio rest devuelve un json de tipo any por lo que tendremos que castearlo al tipo que necesitamos
  }

  /* Get company info */
  getCompany(id_company: number): Observable<Empresa> {
    return this.http.get<Empresa>(this.urlEndPointGetCompany + "/" + id_company);
  }

}
