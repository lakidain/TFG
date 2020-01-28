import { Injectable } from '@angular/core';
import { Cita } from './cita';
import { Usuario } from '../../usuario/login/usuario';
import { Message } from './message';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class CitaService {

  private urlEndPointCitaCreate: string = 'http://localhost:8080/api/cita';
  private urlEndPointAppointmentsList: string = 'http://localhost:8080/api/allCitas';
  private urlEndPointAuditAppointmentsList: string = 'http://localhost:8080/api/auditCitas';
  private urlEndPointPostMessage: string = 'http://localhost:8080/api/message';
  private urlEndPointGetMessages: string = 'http://localhost:8080/api/message';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  /* Create Appointment */
  createCita(cita: Cita): Observable<any> {
    return this.http.post<any>(this.urlEndPointCitaCreate, cita, { headers: this.httpHeaders });
  }

  /* Get ALL List of appointments for an auditor */
  getAppointmentList(usuario: Usuario): Observable<Cita[]>{
    return this.http.get<Cita[]>(this.urlEndPointAppointmentsList+ "/" + usuario.id);
  }

  /* Get List of appointments for an auditor and a concrete audit*/
  getAuditAppointmentList(seleccionAudit: DtoAuditList): Observable<Cita[]>{
    return this.http.get<Cita[]>(this.urlEndPointAuditAppointmentsList+ "/" + seleccionAudit.id_audit);
  }

  /* Add a message to an appointment */
  /* Create Appointment */
  postMessage(message: Message): Observable<any> {
    return this.http.post<any>(this.urlEndPointPostMessage, message, { headers: this.httpHeaders });
  }

  getMessages(cita: Cita): Observable<Message[]>{
    return this.http.get<Message[]>(this.urlEndPointGetMessages+ "/" + cita.id_appointment);
  }
}
