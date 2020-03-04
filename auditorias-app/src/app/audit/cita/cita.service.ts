import { Injectable } from '@angular/core';
import { Cita } from './cita';
import { Usuario } from '../../usuario/login/usuario';
import { Message } from './message';
import { Gallery } from './gallery';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { of, Observable, throwError } from 'rxjs'; //Podemos importar varias cosas a la vez
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
import Swal from 'sweetalert2';
//import {map} from '...' Importacion necesaria para hacer de la otra forma la peticion a la url del get

@Injectable() //El decorador indica que funcion cumple, injectable simboliza que va a ser servicio (modelo de negocio)
export class CitaService {

  private urlEndPointCitaCreate: string = 'http://localhost:8080/api/cita';
  private urlEndPointAppointmentsList: string = 'http://localhost:8080/api/allCitas';
  private urlEndPointAppointmentsRelatedList: string = 'http://localhost:8080/api/allCitasRelated';
  private urlEndPointAuditAppointmentsList: string = 'http://localhost:8080/api/auditCitas';
  private urlEndPointPostMessage: string = 'http://localhost:8080/api/message';
  private urlEndPointGetMessages: string = 'http://localhost:8080/api/message';
  private urlEndPointUpload: string = 'http://localhost:8080/api/cita';
  private urlEndPointGetGallery: string = 'http://localhost:8080/api/uploads/gallery';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  /* Create Appointment */
  createCita(cita: Cita): Observable<any> {
    return this.http.post<any>(this.urlEndPointCitaCreate, cita, { headers: this.httpHeaders });
  }

  /* Get Appointments related for an employee of an audited company */
  getAppointmentRelated(usuario:Usuario): Observable<Cita[]>{
    return this.http.get<Cita[]>(this.urlEndPointAppointmentsRelatedList + "/" + usuario.id);
  }

  /* Get ALL List of appointments for an auditor */
  getAppointmentList(usuario: Usuario): Observable<Cita[]> {
    return this.http.get<Cita[]>(this.urlEndPointAppointmentsList + "/" + usuario.id);
  }

  /* Get List of appointments for an auditor and a concrete audit*/
  getAuditAppointmentList(seleccionAudit: DtoAuditList): Observable<Cita[]> {
    return this.http.get<Cita[]>(this.urlEndPointAuditAppointmentsList + "/" + seleccionAudit.id_audit);
  }

  /* Add a message to an appointment */
  postMessage(message: Message): Observable<any> {
    return this.http.post<any>(this.urlEndPointPostMessage, message, { headers: this.httpHeaders });
  }

  /* Get Message List for an appointment */
  getMessages(cita: Cita): Observable<Message[]> {
    return this.http.get<Message[]>(this.urlEndPointGetMessages + "/" + cita.id_appointment);
  }

  /* Upload photo for an appointment*/
  subirFoto(archivo: File, id, textoEvidencia: string): Observable<string> {
    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);
    formData.append("description_gallery", textoEvidencia)
    return this.http.post(`${this.urlEndPointUpload}/uploads`, formData).pipe(
      map((response: any) => response.mensaje as string),
      catchError(e => {
        console.error(e.error.mesaje);
        Swal.fire(e.error.mesaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  /* Get all photos related to an appointment*/
  getFotos(idAppointment: number): Observable <Gallery[]>{
    return this.http.get<Gallery[]>(this.urlEndPointGetGallery+"/"+idAppointment);
  }

  /* Delete message with an id*/
  deleteMessage(id_message: number): Observable<any>{
    return this.http.delete<any>(this.urlEndPointGetMessages+"/"+id_message, {headers: this.httpHeaders});
  }

  /* Update Appointment to state 1 (closed) */
  cerrarCita(id_appointment: number): Observable<any>{
      return this.http.put<any>(this.urlEndPointCitaCreate + "/" + id_appointment, { headers: this.httpHeaders })
  }
}
