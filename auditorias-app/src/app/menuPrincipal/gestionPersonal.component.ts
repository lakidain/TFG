import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../usuario/login/auth.service';
import { Usuario } from '../usuario/login/usuario';
import { Empresa } from '../empresa/empresa';
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
  usuario:Usuario;
  seleccionUsuario:number;
  seleccionEmpresa:String;

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
    console.log(this.seleccionUsuario);
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
