import { Component } from '@angular/core';
import { ReportService } from './report.service';
import { AuthService } from '../usuario/login/auth.service';
import { Usuario } from '../usuario/login/usuario';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-report',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})

export class ReportComponent {

  listaInformes: any [] = [];
  usuario: Usuario;

  /* Paginacion */
  page: number = 1;

  constructor(private reportService: ReportService, private authService: AuthService) {
    this.usuario = authService.usuario;
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.updateInformes();
  }

  updateInformes() {
    if (this.authService.hasRole('ROLE_AUDITORBOSS')) {
      this.reportService.getCompanyAuditorBoss(this.usuario.id_company).subscribe(
        listaInformes => {
          this.listaInformes = listaInformes;
        }
      );
    } else if (this.authService.hasRole('ROLE_AUDITEDBOSS')) {
      this.reportService.getCompanyAuditedBoss(this.usuario.id_company).subscribe(
        listaInformes => {
          this.listaInformes = listaInformes;
        }
      );
    } else if (this.authService.hasRole('ROLE_AUDITOR') && !this.authService.hasRole('ROLE_AUDITORBOSS')) {
      this.reportService.getReportsAuditor(this.usuario.id).subscribe(
        listaInformes => {
          this.listaInformes = listaInformes;
        }
      );
    } else if (this.authService.hasRole('ROLE_AUDITED') && !this.authService.hasRole('ROLE_AUDITEDBOSS')) {
      this.reportService.getReportsAudited(this.usuario.id).subscribe(
        listaInformes => {
          this.listaInformes = listaInformes;
        }
      );
    }
  }

  downloadFile(route_report){
    window.open("http://localhost:8080/api/report/"+route_report, "_blank");
  }
}
