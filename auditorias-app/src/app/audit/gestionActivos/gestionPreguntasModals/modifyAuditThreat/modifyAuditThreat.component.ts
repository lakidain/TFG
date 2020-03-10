import { Component, OnInit, Input } from '@angular/core';
import { AuditThreat } from '../../../AuditThreat';
import Swal from 'sweetalert2';

import { ModalModifyAuditThreat } from './modalModifyAuditThreat.service';
import { GestionPreguntas } from '../../gestionPreguntas.component';

@Component({
  selector: 'app-auditThreatModify',
  templateUrl: './modifyAuditThreat.component.html',
  styleUrls: ['./modifyAuditThreat.component.css']
})

export class AuditThreatModifyComponent {

  @Input() auditThreat: AuditThreat;

  constructor(private modalModifyAuditThreat: ModalModifyAuditThreat, private gestionPreguntas: GestionPreguntas) { }

  modificarAuditThreat() {
    this.modalModifyAuditThreat.updateAuditThreat(this.auditThreat).subscribe(
      response => {
        Swal.fire('Threat updated', 'Threat correct update', 'success');
        this.cerrarModal();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating the appointment', 'Try again later', 'error');
        }
      }
    );
  }

  cerrarModal() {
    this.gestionPreguntas.updateThreatsRelatedToAsset();
    this.gestionPreguntas.updateAuditThreats();
    this.modalModifyAuditThreat.cerrarModal();
  }
}
