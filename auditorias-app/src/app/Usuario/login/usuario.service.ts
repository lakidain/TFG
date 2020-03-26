import { Injectable } from '@angular/core';
import { Usuario } from './usuario';
import { DtoPassword } from '../../dto/dtoPassword';
import { DtoRegistro } from '../../dto/dtoRegistro';
import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

import { URL_BACKEND } from '../../config/config';

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class UsuarioService {

  private urlEndPoint: string = URL_BACKEND + '/api/usuario'
  private urlEndPointEnable: string = URL_BACKEND + '/api/usuarioEnable'
  private urlEndPointChangePassword: string = URL_BACKEND + '/api/usuarioPassword';
  private urlEndPointChangeCompany: string = URL_BACKEND + '/api/usuarioCompany';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  getAuditores(): Observable<Usuario[]> { //Para que las peticiones sean asincronas hay que implementar observable que hace que nuestro arreglo sea un stream
    //return of(CLIENTES); //Tenemos el api rest con el backend, cuando cambia algo en el servidor directamente se cambia en el cliente sin recargar
    return this.http.get<Usuario[]>(this.urlEndPoint); //El servicio rest devuelve un json de tipo any por lo que tendremos que castearlo al tipo que necesitamos
    //map(response => response as Cliente[])
  }

  create(dtoRegistro: DtoRegistro): Observable<any> { //Lo primero es lo que recibe y lo segundo lo que retorna
    return this.http.post<any>(this.urlEndPoint, dtoRegistro, { headers: this.httpHeaders })
  }

  updateUsuario(usuario: Usuario): Observable<Usuario> { //Lo primero es lo que recibe y lo segundo lo que retorna
    return this.http.put<Usuario>(this.urlEndPoint + "/" + usuario.id, usuario, { headers: this.httpHeaders })
  }

  enableUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(this.urlEndPointEnable + "/" + usuario.id, usuario, { headers: this.httpHeaders })
  }

  updatePassword(usuario: Usuario, dtoPassword: DtoPassword): Observable<any> {
    return this.http.put<any>(this.urlEndPointChangePassword + "/" + usuario.id, dtoPassword, { headers: this.httpHeaders })
  }

  updateCompany(usuario: Usuario, id_company: number): Observable<any> {
    return this.http.put<any>(this.urlEndPointChangeCompany + "/" + usuario.id, id_company, { headers: this.httpHeaders })
  }
}
