import { Injectable } from '@angular/core';
import { Usuario } from '../usuario/login/usuario';
import { Empresa } from '../empresa/empresa';
import { Audit } from '../audit/audit';
import { AuditType } from '../audit/auditType';
import { DtoAuditList } from '../dto/dtoAuditList';

import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class GestionPersonalService {

  private urlEndPointEmpleados: string = 'http://localhost:8080/api/empleados';
  private urlEndPointEmpresas: string = 'http://localhost:8080/api/empresasAudited';
  private urlEndPointAuditTypes: string = 'http://localhost:8080/api/auditTypes';
  private urlEndPointAudit: string = "http://localhost:8080/api/audit";
  private urlEndPointCompanyAuditList: string = "http://localhost:8080/api/auditCompany";

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })

  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  /* Dada la empresa del empleado actual devuelve los empleados que trabajan en esta */
  getEmpleados(usuario: Usuario): Observable<Usuario[]> { //Para que las peticiones sean asincronas hay que implementar observable que hace que nuestro arreglo sea un stream
    return this.http.get<Usuario[]>(this.urlEndPointEmpleados + "/" + usuario.id_company); //El servicio rest devuelve un json de tipo any por lo que tendremos que castearlo al tipo que necesitamos
  }

  /* Dada la empresa devuelve las auditorias relacionadas con esta */
  getAudits(id_company:number): Observable <DtoAuditList[]>{
    return this.http.get<DtoAuditList[]>(this.urlEndPointCompanyAuditList+"/"+id_company);
  }

  /* Metodo que devuelve las empresas posibles para auditar*/
  getEmpresas(): Observable<Empresa[]> { //Para que las peticiones sean asincronas hay que implementar observable que hace que nuestro arreglo sea un stream
    return this.http.get<Empresa[]>(this.urlEndPointEmpresas); //El servicio rest devuelve un json de tipo any por lo que tendremos que castearlo al tipo que necesitamos
  }

  /* Metodo que devuelve el tipo de auditorias existentes*/
  getAuditTypes(): Observable<AuditType[]> {
    return this.http.get<AuditType[]>(this.urlEndPointAuditTypes);
  }

  /* Método para la creación de una auditoría */
  setAudit(audit: Audit): Observable<any> {
    return this.http.post<any>(this.urlEndPointAudit, audit, { headers: this.httpHeaders });
  }

  /* Delete an Audit */
  deleteAudit(id_audit: number): Observable<any>{
    return this.http.delete<any>(this.urlEndPointAudit+"/"+id_audit, {headers: this.httpHeaders});
  }
}
