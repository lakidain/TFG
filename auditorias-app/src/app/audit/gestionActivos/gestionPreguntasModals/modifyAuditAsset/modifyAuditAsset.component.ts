import { Component, OnInit, Input } from '@angular/core';
import { AuditAsset } from '../../../AuditAsset';
import Swal from 'sweetalert2';

import { ModalModifyAuditAsset } from './modalModifyAuditAsset.service';
import { GestionPreguntas } from '../../gestionPreguntas.component';

@Component({
  selector: 'app-auditAssetModify',
  templateUrl: './modifyAuditAsset.component.html',
  styleUrls: ['./modifyAuditAsset.component.css']
})

export class AuditAssetModifyComponent {

  @Input() auditAsset: AuditAsset;

  constructor(public modalModifyAuditAsset: ModalModifyAuditAsset, public gestionPreguntas: GestionPreguntas) { }

  modificarAuditAsset() {
    this.modalModifyAuditAsset.updateAuditAsset(this.auditAsset).subscribe(
      response => {
        Swal.fire('Asset updated', 'Asset correct update', 'success');
        this.cerrarModal();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating the asset', 'Try again later', 'error');
        }
      }
    );
  }

  cerrarModal() {
    this.gestionPreguntas.updateAssetsRelatedToType();
    this.gestionPreguntas.updateAuditAssets();
    this.modalModifyAuditAsset.cerrarModal();
  }
}
