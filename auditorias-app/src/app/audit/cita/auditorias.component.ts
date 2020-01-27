import { Component } from '@angular/core';
import {  AuditoriaService } from './auditorias.service';
import { Audit } from '../audit';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { Usuario } from '../../usuario/login/usuario';
import { AuthService } from 'src/app/usuario/login/auth.service';
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
  /* Calendar inicializations*/
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin]; // important!

  public autor: any = { nombre: 'Ander', apellido: 'Lakidain' }; //any es objetos que no sean de una clase particular, por defecto si no se define es public

  constructor(private authService: AuthService, private auditoriaService: AuditoriaService) {
    this.usuario = authService.usuario
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.auditoriaService.getAuditsAssigned(this.usuario).subscribe(
      auditList => {
        this.auditList = auditList;
      }
    );
  }
}
