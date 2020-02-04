import { Component, OnInit } from '@angular/core';
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

  private empresa: Empresa = new Empresa();

  constructor(private companyService: CompanyService, private router: Router, private modalCompanyToAudit:ModalCompanyToAudit) { //Este metodo constructor inicializa de forma normal
  }

  ngOnInit() {
  }

  public create(): void {
    this.companyService.create(this.empresa).subscribe(
      response => {
        Swal.fire('Empresa Registrada', `La creacion de la nueva empresa ha sido un exito`, 'success');
        this.router.navigate(['/gestionPersonal']) //Para navegar cuando devuelve el objeto creado te redirige al menu
      }, err => {
        Swal.fire('Error', `El registro ha fallado, vuelva a intentarlo`, 'error');
      }
    )
  }

  cerrarModal() {
    this.modalCompanyToAudit.cerrarModal();
  }

}
