import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../usuario/login/usuario';
import { Empresa } from '../empresa/empresa';
import { Audit } from '../audit/audit';
import { AuditType } from '../audit/auditType';
import Swal from 'sweetalert2'

import { GestionPersonalService } from './gestionPersonal.service';
import { UsuarioService } from '../usuario/login/usuario.service';
import { AuthService } from '../usuario/login/auth.service';

@Component({
  selector: 'app-perfil',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './gestionPersonal.component.html',
  styleUrls: ['./gestionPersonal.component.css']
})

export class GestionPersonalComponent{

  empleados:Usuario[];
  empresas:Empresa[];
  tipoAuditorias:AuditType[];
  audit: Audit;
  usuario:Usuario;

  /* Atributos de seleccion del formulario */
  seleccionUsuario: number;
  seleccionEmpresa: number;
  seleccionTipoAuditoria: number;
  seleccionFechaInicio: Date;
  seleccionFechaFin: Date;

  constructor(private authService: AuthService, private gestionPersonalService:GestionPersonalService, private usuarioService:UsuarioService,private router: Router){ //Este metodo constructor inicializa de forma normal
    this.usuario= authService.usuario; //Y este tambien es valido, se puede hacer de las dos formas
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.updateEmpleados();
    this.gestionPersonalService.getEmpresas().subscribe(
      empresas =>{
        this.empresas = empresas;
      }
    );
    this.gestionPersonalService.getAuditTypes().subscribe(
      types=>{
        this.tipoAuditorias=types;
      }
    );
  }

  updateEmpleados():void{
    this.gestionPersonalService.getEmpleados(this.usuario).subscribe(
      empleados =>{
        this.empleados = empleados;
      }
    );
  }

  enviar():void{
    this.audit = new Audit();
    this.audit.id_user_manager=this.seleccionUsuario;
    this.audit.id_company_auditing=this.usuario.id_company;
    this.audit.id_company_audited=this.seleccionEmpresa;
    this.audit.id_audit_type=this.seleccionTipoAuditoria;
    this.audit.date_start_audit=this.seleccionFechaInicio;
    this.audit.date_end_audit=this.seleccionFechaFin;

    /*this.gestionPersonalService.setAudit(this.audit).subscribe( response => {
      if(response){
        Swal.fire('Exito al crear la auditoría', 'La auditoria ha sido creada', 'success');
        this.router.navigate(['/menu']);
      } else{
        Swal.fire('Error al crear la auditoria', 'Vuelva a intentar crearla', 'error');
        }
      }
    )*/

    this.gestionPersonalService.setAudit(this.audit).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
          Swal.fire('Exito al crear la auditoría', 'La auditoria ha sido creada', 'success');
          this.router.navigate(['/menu']);
          }, err => {
          if(err.status==400 || err.status==401){
            Swal.fire('Error al crear la auditoria', 'Vuelva a intentar crearla', 'error');
          }
        }
      )
    }

  isEnabled(empleado:Usuario):boolean{
    if(empleado.enabled){
      return true;
    }
    else{
      return false;
    }
  }

  aceptarPersona(empleado:Usuario):void{
    this.usuarioService.enableUsuario(empleado).subscribe(
      response => {
        Swal.fire('Usuario Aceptado', `Ha aceptado al empleado con DNI ${empleado.username}`,'success');
        this.updateEmpleados();
      }
    );
  }

  /* Info events when info button clicked*/
  infoAuditCreation(){
    Swal.fire('Information', 'Create an Audit introducing the information requested. If the company to be audited it\'s not created, you should register his boss and the company in the Register pannel', 'info');
  }

  infoEmployeeTable(){
    Swal.fire('Information', 'Table with employees. You can accept a request from a new employee clicking the button in the last box', 'info');
  }
}
