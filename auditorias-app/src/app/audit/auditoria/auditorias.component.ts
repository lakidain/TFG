import { Component } from '@angular/core';
import {  AuditoriaService } from './auditorias.service';
import { Audit } from '../audit';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { Usuario } from '../../usuario/login/usuario';
import { AuthService } from 'src/app/usuario/login/auth.service';

/* Modals */
import { ModalCita } from '../cita/modalCita.service';
import { ModalEmployee } from '../cita/modalEmployee.service';

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

  auditList : DtoAuditList[];
  usuario: Usuario;
  seleccionAudit: DtoAuditList;
  auditCitaCreation: DtoAuditList;
  auditEmployeeAdd: DtoAuditList;
  /* Calendar inicializations*/
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin]; // important!

  constructor(private authService: AuthService, private auditoriaService: AuditoriaService, private modalCita:ModalCita, private modalEmployee: ModalEmployee) {
    this.usuario = authService.usuario
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.auditoriaService.getAuditsAssigned(this.usuario).subscribe(
      auditList => {
        this.auditList = auditList;
      }
    );
  }

  abrirModalAddAppointment(){
    this.auditCitaCreation = this.seleccionAudit;
    this.modalCita.abrirModal();
  }

  abrirModalAddEmployee(){
    this.auditEmployeeAdd = this.seleccionAudit;
    this.modalEmployee.abrirModal();
  }

}
