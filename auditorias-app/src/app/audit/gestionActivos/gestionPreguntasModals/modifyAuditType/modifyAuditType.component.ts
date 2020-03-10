import { Component, OnInit, Input } from '@angular/core';
import { AuditType } from 'src/app/audit/auditType';
import Swal from 'sweetalert2';

import { ModalModifyAuditType } from './modalModifyAuditType.service';
import { GestionPreguntas } from '../../gestionPreguntas.component';

@Component({
  selector: 'app-auditTypeModify',
  templateUrl: './modifyAuditType.component.html',
  styleUrls: ['./modifyAuditType.component.css']
})

export class AuditTypeModifyComponent {

  @Input() auditType: AuditType;

  constructor(private modalModifyAuditType: ModalModifyAuditType, private gestionPreguntas: GestionPreguntas) { }

  modificarAuditType() {
    this.modalModifyAuditType.updateAuditType(this.auditType).subscribe(
      response => {
        Swal.fire('Type updated', 'Type correct update', 'success');
        this.cerrarModal();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating the appointment', 'Try again later', 'error');
        }
      }
    );
  }

  cerrarModal() {
    this.gestionPreguntas.updateAuditTypes();
    this.modalModifyAuditType.cerrarModal();
  }
}
