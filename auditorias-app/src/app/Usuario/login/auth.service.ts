import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Usuario } from './usuario';

import { URL_BACKEND } from '../../config/config';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private _usuario: Usuario;
  private _token: string;

  constructor(private http: HttpClient, public router: Router) { }

  public get usuario(): Usuario { //Metodo getter
    if (this._usuario != null) {
      return this._usuario;
    } else if (this._usuario == null && sessionStorage.getItem('usuario') != null) {
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario; //Casting de json a tipo Usuario
      return this._usuario;
    }
    return new Usuario();
  }

  public get token(): string {
    if (this._token != null) {
      return this._token;
    } else if (this._usuario == null && sessionStorage.getItem('token') != null) {
      this._token = sessionStorage.getItem('token'); //Casting de json a tipo Usuario
      return this._token;
    }
    return null;
  }

  login(usuario: Usuario): Observable<any> {
    const urlEndPoint = URL_BACKEND + '/oauth/token';

    const credenciales = btoa('angularapp' + ':' + '12345');

    const httpHeaders = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Basic ' + credenciales });

    let params = new URLSearchParams();
    params.set('grant_type', 'password');
    params.set('username', usuario.username);
    params.set('password', usuario.password);
    return this.http.post<any>(urlEndPoint, params.toString(), { headers: httpHeaders });
  }

  guardarUsuario(accessToken: string): void {
    let payload = this.obtenerDatosToken(accessToken);
    this._usuario = new Usuario();
    this._usuario.id = payload.id;
    this._usuario.name_user = payload.name;
    this._usuario.username = payload.user_name;
    this._usuario.email_user = payload.email;
    this._usuario.phone_user = payload.phone;
    this._usuario.id_company = payload.companyId;
    this._usuario.roles = payload.authorities;
    sessionStorage.setItem('usuario', JSON.stringify(this._usuario));
  }

  changeEmailAndPhone(usuario: Usuario): void {
    this._usuario.email_user = usuario.email_user;
    this._usuario.phone_user = usuario.phone_user;
    sessionStorage.setItem('usuario', JSON.stringify(this._usuario)); /* When changes made is important to save in session! */
  }

  guardarToken(accessToken: string): void {
    this._token = accessToken;
    sessionStorage.setItem('token', accessToken);
  }

  obtenerDatosToken(accessToken: string): any {
    if (accessToken != null) {
      return JSON.parse(atob(accessToken.split(".")[1]));
    }
    return null;
  }

  isAuthenticated(): boolean {
    let payload = this.obtenerDatosToken(this.token);
    if (payload != null && payload.user_name && payload.user_name.length > 0) {
      return true;
    }
    return false;
  }

  hasRole(role: string): boolean {
    if (this.usuario.roles.includes(role)) {
      return true;
    }
    return false;
  }

  logout(): void {
    this._token = null;
    this._usuario = null;
    sessionStorage.clear();
    this.router.navigate(['/index']);

    setTimeout(() => {
      window.location.reload();
    }
      , 500);
  }
}
