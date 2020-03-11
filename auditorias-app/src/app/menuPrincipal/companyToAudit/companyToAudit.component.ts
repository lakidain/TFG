import { Component, OnInit } from '@angular/core';
import { GestionPersonalComponent } from '../gestionPersonal.component';
import { ModalCompanyToAudit } from './modalCompanyToAudit.service';
import { Empresa } from '../../empresa/Empresa';
import { Router } from '@angular/router';
import { CompanyService } from '../../empresa/company.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-newCompany',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './companyToAudit.component.html',
  styleUrls: ['./companyToAudit.component.css']
})

export class CompanyToAuditComponent implements OnInit {

  public empresa: Empresa = new Empresa();

  constructor(public companyService: CompanyService, public router: Router, public modalCompanyToAudit: ModalCompanyToAudit, public gestionPersonalComponent: GestionPersonalComponent) { //Este metodo constructor inicializa de forma normal
  }

  ngOnInit() {
  }

  public create(): void {
    this.companyService.create(this.empresa).subscribe(
      response => {
        Swal.fire('Company registered', `The creation of the new company has been a success`, 'success');
        this.cerrarModal();
      }, err => {
        Swal.fire('Error', `Register failed, try again later`, 'error');
      }
    )
  }

  cerrarModal() {
    this.gestionPersonalComponent.updateCompanies();
    this.modalCompanyToAudit.cerrarModal();
  }

}
