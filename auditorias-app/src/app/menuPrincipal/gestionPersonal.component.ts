import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../usuario/login/auth.service';
import { Usuario } from '../usuario/login/usuario';
import { Empresa } from '../empresa/empresa';
import { Audit } from '../audit/audit';
import { AuditType } from '../audit/auditType';
import Swal from 'sweetalert2'

import { GestionPersonalService } from './gestionPersonal.service';
import { UsuarioService } from '../usuario/login/usuario.service';

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
    this.audit.id_company=this.seleccionEmpresa;
    this.audit.id_audit_type=this.seleccionTipoAuditoria;
    this.audit.date_start_audit=this.seleccionFechaInicio;
    this.audit.date_end_audit=this.seleccionFechaFin;

    this.gestionPersonalService.setAudit(this.audit).subscribe( response => {
      if(response){
        Swal.fire('Exito al crear la auditoría', 'La auditoria ha sido creada', 'success');
        this.router.navigate(['/menu']);
      } else{
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
}
