import { Component, OnInit, Input } from '@angular/core';
import { Audit } from '../../audit/audit';
import { GestionPersonalComponent } from '../gestionPersonal.component';
import Swal from 'sweetalert2';

import { ModalModifyAudit } from './modalModifyAudit.service';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { Usuario } from '../../usuario/login/usuario';

@Component({
  selector: 'app-AuditModify',
  templateUrl: './modifyAudit.component.html',
  styleUrls: ['./modifyAudit.component.css']
})

export class AuditModifyComponent {

  @Input() audit: DtoAuditList;
  @Input() empleados: Usuario[];

  modifiedAudit: Audit;

  constructor(public modalModifyAudit: ModalModifyAudit, public gestionPersonalComponent: GestionPersonalComponent) {
    this.modifiedAudit = new Audit();
  }

  modificarAudit() {
    this.modifiedAudit.date_end_audit = this.audit.date_end_audit;
    this.modifiedAudit.date_start_audit = this.audit.date_start_audit;
    this.modifiedAudit.id_audit = this.audit.id_audit;
    this.modifiedAudit.id_audit_type = this.audit.id_audit_type;
    this.modifiedAudit.id_company_audited = this.audit.id_company_audited;
    this.modifiedAudit.id_company_auditing = this.audit.id_company_auditing;
    this.modifiedAudit.id_user_manager = this.audit.id_user_manager;

    this.modalModifyAudit.updateAudit(this.modifiedAudit).subscribe(
      response => {
        Swal.fire('Audit updated', 'Audit correct update', 'success');
        this.cerrarModal();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating the appointment', 'Try again later', 'error');
        }
      }
    );
  }

  cerrarModal() {
    this.gestionPersonalComponent.updateAudits();
    this.modalModifyAudit.cerrarModal();
  }
}
