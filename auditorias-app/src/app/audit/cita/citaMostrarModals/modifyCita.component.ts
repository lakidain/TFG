import { Component, OnInit, Input } from '@angular/core';
import { Cita } from '../cita';
import Swal from 'sweetalert2';

import { ModalModifyCita } from './modalModifyCita.service';
import { AuditoriasComponent } from '../../auditoria/auditorias.component';

@Component({
  selector: 'app-appointmentModify',
  templateUrl: './modifyCita.component.html',
  styleUrls: ['./modifyCita.component.css']
})

export class CitaModifyComponent {

  @Input() cita: Cita;

  constructor(private modalModifyCita: ModalModifyCita, private auditoriasComponent: AuditoriasComponent) { }

  modificarCita() {
    this.modalModifyCita.updateAppointment(this.cita).subscribe(
      response => {
        Swal.fire('Appointment updated', 'Appointment correct update', 'success');
        this.cerrarModal();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating the appointment', 'Try again later', 'error');
        }
      }
    );
  }

  cerrarModal() {
    this.auditoriasComponent.actualizarListaCitas();
    this.auditoriasComponent.actualizarCalendarioCitas();
    this.modalModifyCita.cerrarModal();
  }
}
