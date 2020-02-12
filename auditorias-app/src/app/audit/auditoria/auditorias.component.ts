import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Audit } from '../audit';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { Usuario } from '../../usuario/login/usuario';
import { Cita } from '../cita/cita';

/* Services */
import { AuthService } from 'src/app/usuario/login/auth.service';
import { AuditoriaService } from './auditorias.service';
import { CitaService } from '../cita/cita.service';

/* Modals */
import { ModalCrearCita } from '../cita/modalCrearCita.service';
import { ModalEmployee } from '../cita/modalEmployee.service';
import { ModalMostrarCita } from '../cita//modalMostrarCita.service';

/* CALENDAR */
import { Calendar } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGrigPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction'; // for dateClick

@Component({
  selector: 'app-citas',
  templateUrl: './auditorias.component.html',
  styleUrls: ['./auditorias.component.css']
})

export class AuditoriasComponent {

  auditList: DtoAuditList[];
  allAppointments: Cita[];
  auditAppointments: Cita[];
  usuario: Usuario;
  seleccionAudit: DtoAuditList;
  auditCitaCreation: DtoAuditList;
  auditEmployeeAdd: DtoAuditList;
  appointmentSelected: Cita;

  /* Calendar inicializations*/
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin]; // important!
  calendarEvents = [
    //{ title: 'event 1', date: '2020-01-01' } EJEMPLO FORMATO
  ];

  constructor(private authService: AuthService, private auditoriaService: AuditoriaService, private citaService: CitaService, private modalMostrarCita: ModalMostrarCita, private modalCrearCita: ModalCrearCita, private modalEmployee: ModalEmployee, private datePipe: DatePipe) {
    this.usuario = authService.usuario;
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.actualizarCalendarioCitas();
    this.auditoriaService.getAuditsAssigned(this.usuario).subscribe(
      auditList => {
        this.auditList = auditList;
      }
    );
  }

  actualizarCalendarioCitas() {
    this.calendarEvents = [];
    this.citaService.getAppointmentList(this.usuario).subscribe(
      allAppointments => {
        if (allAppointments.length > 0) {
          this.calendarEvents = allAppointments.map(cita => {
            return { title: cita.name_appointment, date: this.transformDate(cita.date_appointment) };
          });
        }
      }
    );
  }


  actualizarListaCitas() {
    this.auditAppointments = [];
    this.citaService.getAuditAppointmentList(this.seleccionAudit).subscribe(
      allAppointments => {
        this.auditAppointments = allAppointments;
      }
    );
  }

  abrirModalAddAppointment() {
    this.auditCitaCreation = this.seleccionAudit;
    this.modalCrearCita.abrirModal();
  }

  abrirModalAddEmployee() {
    this.auditEmployeeAdd = this.seleccionAudit;
    this.modalEmployee.abrirModal();
  }

  abrirModalCita(appointment: Cita) {
    this.appointmentSelected = appointment;
    this.modalMostrarCita.abrirModal();
  }

  transformDate(date) {
    return this.datePipe.transform(date, 'yyyy-MM-dd HH:mm', 'GMT');
  }

}
