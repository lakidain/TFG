import { Component, OnInit, Input } from '@angular/core';
import { Audit } from '../../audit/audit';
import { PerfilUsuarioComponent } from '../perfilUsuario.component';
import Swal from 'sweetalert2';

import { ModalModifyCompany } from './modalModifyCompany.service';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { Usuario } from '../../usuario/login/usuario';
import { Empresa } from '../../empresa/empresa';

@Component({
  selector: 'app-CompanyModify',
  templateUrl: './modifyCompany.component.html',
  styleUrls: ['./modifyCompany.component.css']
})

export class CompanyModifyComponent {

  @Input() company: Empresa;

  constructor(private modalModifyCompany: ModalModifyCompany, private perfilUsuarioComponent: PerfilUsuarioComponent) {
  }

  modificarCompany() {
    this.modalModifyCompany.updateCompany(this.company).subscribe(
      response => {
        Swal.fire('Company updated', 'Company correct update', 'success');
        this.cerrarModal();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating the appointment', 'Try again later', 'error');
        }
      }
    );
  }

  cerrarModal() {
    this.perfilUsuarioComponent.userCompany();
    this.modalModifyCompany.cerrarModal();
  }
}
