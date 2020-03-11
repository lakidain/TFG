import { Component, OnInit, Input } from '@angular/core';
import { ModalCrearCita } from './modalCrearCita.service';
import { AuditoriasComponent } from '../auditoria/auditorias.component';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { Cita } from './cita';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

/* Services */
import { CitaService } from './cita.service';

@Component({
  selector: 'app-citaCreation',
  templateUrl: './citaCreation.component.html',
  styleUrls: ['./citaCreation.component.css']
})

export class CitaCreationComponent {

  @Input() audit: DtoAuditList;
  public cita: Cita;

  constructor(public modalCrearCita: ModalCrearCita, public citaService: CitaService, public router: Router, public auditoriasComponent: AuditoriasComponent) {
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.cita = new Cita();
  }

  create() {
    this.cita.id_audit = this.audit.id_audit;
    this.citaService.createCita(this.cita).subscribe(
      response => {
        Swal.fire('Appointment Registered', `New appointment created succesfully`, 'success');
        this.auditoriasComponent.actualizarCalendarioCitas();
        this.auditoriasComponent.actualizarListaCitas();
        this.cerrarModal();
      }, err => {
        Swal.fire('Error', `Creation failed, try again later`, 'error');
      }
    )
  }

  cerrarModal() {
    this.cita = new Cita(); /* Si se cierra el Modal perdemos reseteamos los campos */
    this.modalCrearCita.cerrarModal();
  }
}
