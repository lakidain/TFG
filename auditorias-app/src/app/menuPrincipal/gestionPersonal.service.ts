import { Injectable } from '@angular/core';
import { Usuario } from '../usuario/login/usuario';
import { Empresa } from '../empresa/empresa';

import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient,HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class GestionPersonalService {

  private urlEndPointEmpleados : string = 'http://localhost:8080/api/empleados';
  private urlEndPointEmpresas : string = 'http://localhost:8080/api/empresas';

  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  getEmpleados(usuario:Usuario): Observable <Usuario[]> { //Para que las peticiones sean asincronas hay que implementar observable que hace que nuestro arreglo sea un stream
    return this.http.get<Usuario[]>(this.urlEndPointEmpleados+"/"+usuario.name_company); //El servicio rest devuelve un json de tipo any por lo que tendremos que castearlo al tipo que necesitamos
  }
  getEmpresas(): Observable <Empresa[]> { //Para que las peticiones sean asincronas hay que implementar observable que hace que nuestro arreglo sea un stream
    return this.http.get<Empresa[]>(this.urlEndPointEmpresas); //El servicio rest devuelve un json de tipo any por lo que tendremos que castearlo al tipo que necesitamos
  }
}
