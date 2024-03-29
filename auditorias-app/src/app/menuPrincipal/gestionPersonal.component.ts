import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../Usuario/login/usuario';
import { Empresa } from '../empresa/empresa';
import { Audit } from '../audit/audit';
import { AuditType } from '../audit/auditType';
import { DtoAuditList } from '../dto/dtoAuditList';
import Swal from 'sweetalert2'

import { GestionPersonalService } from './gestionPersonal.service';
import { UsuarioService } from '../Usuario/login/usuario.service';
import { AuthService } from '../Usuario/login/auth.service';

/* Modals import */
import { ModalCompanyToAudit } from './companyToAudit/modalCompanyToAudit.service';
import { ModalModifyAudit } from './companyToAudit/modalModifyAudit.service';

@Component({
  selector: 'app-perfil',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './gestionPersonal.component.html',
  styleUrls: ['./gestionPersonal.component.css']
})

export class GestionPersonalComponent {

  empleados: Usuario[] = [];
  empresas: Empresa[] = [];
  tipoAuditorias: AuditType[] = [];
  audit: Audit;
  usuario: Usuario;

  /* Atributos de seleccion del formulario */
  seleccionUsuario: number;
  seleccionEmpresa: number;
  seleccionTipoAuditoria: number;
  seleccionFechaInicio: Date;
  seleccionFechaFin: Date;

  /* Modal */
  mostrarModal: boolean;

  /* Lista de auditorias creadas */
  auditList: DtoAuditList[] = [];

  /* Auditoria a Modificar */
  auditModify: DtoAuditList;

  /* Pagination */
  pageEmployees: number = 1;
  pageAudits: number = 1;

  constructor(public authService: AuthService, public gestionPersonalService: GestionPersonalService, public usuarioService: UsuarioService, public router: Router, public modalCompanyToAudit: ModalCompanyToAudit, public modalModifyAudit: ModalModifyAudit) { //Este metodo constructor inicializa de forma normal
    this.usuario = authService.usuario; //Y este tambien es valido, se puede hacer de las dos formas
    this.auditModify = new DtoAuditList();
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.updateEmpleados();
    this.updateAudits();
    this.updateCompanies();
    this.gestionPersonalService.getAuditTypes().subscribe(
      types => {
        this.tipoAuditorias = types;
      }
    );
  }

  updateCompanies() {
    this.gestionPersonalService.getEmpresas().subscribe(
      empresas => {
        this.empresas = empresas;
      }
    );
  }

  updateEmpleados(): void {
    this.gestionPersonalService.getEmpleados(this.usuario).subscribe(
      empleados => {
        this.empleados = empleados;
      }
    );
  }

  updateAudits() {
    this.gestionPersonalService.getAudits(this.usuario.id_company).subscribe(
      audits => {
        this.auditList = audits;
      }
    );
  }

  enviar(): void {
    this.audit = new Audit();
    this.audit.id_user_manager = this.seleccionUsuario;
    this.audit.id_company_auditing = this.usuario.id_company;
    this.audit.id_company_audited = this.seleccionEmpresa;
    this.audit.id_audit_type = this.seleccionTipoAuditoria;
    this.audit.date_start_audit = this.seleccionFechaInicio;
    this.audit.date_end_audit = this.seleccionFechaFin;

    this.gestionPersonalService.setAudit(this.audit).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
      Swal.fire('Success creating the audit', 'Audit has been created', 'success');
      this.updateAudits();
    }, err => {
      if (err.status == 400 || err.status == 401) {
        Swal.fire('Error creating the audit', 'Try again later', 'error');
      }
    }
    )
  }

  modifyAudit(audit: DtoAuditList) {
    this.auditModify = audit;
    this.modalModifyAudit.abrirModal();
  }

  deleteAudit(audit: Audit) { /* Aqui se puede comprobar que se hace un casting directo de DtoAuditList a Audit */
    if (confirm("¿Are you sure you want to remove the audit? Information can be lost in the process")) {
      this.gestionPersonalService.deleteAudit(audit.id_audit).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
        Swal.fire('Success removing the audit', 'The audit has been removed succesfully', 'success');
        this.updateAudits();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error removing audit', 'Try again later', 'error');
        }
      });
    }
  }

  isEnabled(empleado: Usuario): boolean {
    if (empleado.enabled) {
      return true;
    }
    else {
      return false;
    }
  }

  aceptarPersona(empleado: Usuario): void {
    this.usuarioService.enableUsuario(empleado).subscribe(
      response => {
        Swal.fire('User accepted', `You've accepted user with DNI: ${empleado.username}`, 'success');
        this.updateEmpleados();
      }
    );
  }

  /* Info events when info button clicked*/
  infoAuditCreation() {
    Swal.fire('Information', 'Create an Audit introducing the information requested. If the company to be audited it\'s not created, you should register his boss and the company in the Register pannel', 'info');
  }

  infoEmployeeTable() {
    Swal.fire('Information', 'Table with employees. You can accept a request from a new employee clicking the button in the last box', 'info');
  }

  abrirModalCompanyToAudit() {
    this.mostrarModal = true;
    this.modalCompanyToAudit.abrirModal();
  }
}
