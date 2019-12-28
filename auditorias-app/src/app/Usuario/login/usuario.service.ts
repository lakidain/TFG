import { Injectable } from '@angular/core';
import {Usuario} from './usuario';
import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient,HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class UsuarioService {

  private urlEndPoint : string = 'http://localhost:8080/api/usuario'
  private urlEnable: string = 'http://localhost:8080/api/usuarioEnable'

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  getAuditores(): Observable <Usuario[]> { //Para que las peticiones sean asincronas hay que implementar observable que hace que nuestro arreglo sea un stream
    //return of(CLIENTES); //Tenemos el api rest con el backend, cuando cambia algo en el servidor directamente se cambia en el cliente sin recargar
    return this.http.get<Usuario[]>(this.urlEndPoint); //El servicio rest devuelve un json de tipo any por lo que tendremos que castearlo al tipo que necesitamos
    //map(response => response as Cliente[])
  }

  create(usuario : Usuario) : Observable <Usuario> { //Lo primero es lo que recibe y lo segundo lo que retorna
    return this.http.post<Usuario>(this.urlEndPoint, usuario, {headers: this.httpHeaders})
  }

  updateUsuario(usuario : Usuario) : Observable <Usuario> { //Lo primero es lo que recibe y lo segundo lo que retorna
    return this.http.put<Usuario>(this.urlEndPoint+"/"+usuario.id, usuario ,{headers: this.httpHeaders})
  }

  enableUsuario(usuario: Usuario): Observable <Usuario>{
    return this.http.put<Usuario>(this.urlEnable+"/"+usuario.id, usuario ,{headers: this.httpHeaders})
  }
}
