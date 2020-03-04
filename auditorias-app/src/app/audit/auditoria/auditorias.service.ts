import { Injectable } from '@angular/core';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { Usuario } from '../../usuario/login/usuario';
import { DtoAuditEmployee } from '../../dto/dtoAuditEmployee';
import { AuditAsset } from '../auditAsset';
import { Audit_Employees } from '../auditEmployees';
import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class AuditoriaService {

  private urlEndPointAuditList: string = 'http://localhost:8080/api/audit';
  private urlEndPointAuditRelated: string = 'http://localhost:8080/api/auditAudited';
  private urlEndPointAuditEmployees: string = 'http://localhost:8080/api/clientesAssociate';
  private urlEndPointAuditAssets: string = 'http://localhost:8080/api/auditAssets';
  private urlEndPointAppointmentAssets: string = 'http://localhost:8080/api/cita';

  /* Check Credentials */
  private urlEndPointCheckQuestionnaireCredentials: string = 'http://localhost:8080/api/questionnaireCredentials';
  private urlEndPointCheckAppointmentCredentials: string = 'http://localhost:8080/api/appointmentCredentials';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  /* Get Audit List for an Auditor */
  getAuditsAssigned(usuario: Usuario): Observable<DtoAuditList[]> {
    return this.http.get<DtoAuditList[]>(this.urlEndPointAuditList+ "/" + usuario.id);
  }

  /* Get Audit List for an employee for an audited company */
  getAuditsRelated(usuario: Usuario): Observable<DtoAuditList[]> {
    return this.http.get<DtoAuditList[]>(this.urlEndPointAuditRelated+ "/" + usuario.id);
  }

  /* Get Assets for an Audit Type */
  getAssets(id_audit: number):Observable<AuditAsset[]>{
    return this.http.get<AuditAsset[]>(this.urlEndPointAuditAssets+"/"+id_audit);
  }

  /* Get Employees associated with an audit */
  getEmployeesAssigned(id_audit:number): Observable <DtoAuditEmployee[]>{
    return this.http.get<DtoAuditEmployee[]>(this.urlEndPointAuditEmployees+"/"+id_audit);
  }

  /* Update asi_audit_employees */
  updateEmployeesAssigned(auditEmployee: Audit_Employees): Observable<any>{
    return this.http.put<Usuario>(this.urlEndPointAuditEmployees + "/" + auditEmployee.id_audit_employees, auditEmployee, { headers: this.httpHeaders });
  }

  /* Delete Appointment */
  deleteAppointment(id_appointment:number):Observable<any>{
    return this.http.delete<any>(this.urlEndPointAppointmentAssets+"/"+id_appointment, {headers: this.httpHeaders});
  }

  /* Delete Employee Association with the actual appointment*/
  deleteEmployeeFromAppointment(id_audit_employees:number): Observable<any>{
    return this.http.delete<any>(this.urlEndPointAuditEmployees+"/"+id_audit_employees, {headers: this.httpHeaders});
  }

  /* Check Questionnaire credentials */
  checkQuestionnaireCredentials(id,id_audit):Observable<any>{
    return this.http.get<any>(this.urlEndPointCheckQuestionnaireCredentials+"/"+id_audit+"/"+id);
  }

  /* Check Appointment credentials */
  checkAppointmentCredentials(id,id_audit):Observable<any>{
    return this.http.get<any>(this.urlEndPointCheckAppointmentCredentials+"/"+id_audit+"/"+id);
  }
}
