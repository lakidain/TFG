import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'
import { Usuario } from '../usuario/login/usuario';
import { Empresa } from '../empresa/empresa';
import { DtoPassword } from '../dto/dtoPassword';

import { GestionPersonalService } from './gestionPersonal.service';
import { UsuarioService } from '../usuario/login/usuario.service';
import { AuthService } from '../usuario/login/auth.service';

@Component({
  selector: 'app-perfil',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './perfilUsuario.component.html',
  styleUrls: ['./perfilUsuario.component.css']
})

export class PerfilUsuarioComponent{

  usuario:Usuario;
  empresas:Empresa[];

  /* Parameters for the password change */
  dtoPassword: DtoPassword;
  newPassword: string;
  oldPassword: string;

  /* Parameter for the company change */
  seleccionEmpresa: string;

  constructor(private authService: AuthService, private gestionPersonalService:GestionPersonalService,private usuarioService:UsuarioService, private router: Router){ //Este metodo constructor inicializa de forma normal
    this.usuario= authService.usuario; //Y este tambien es valido, se puede hacer de las dos formas
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.gestionPersonalService.getEmpresas().subscribe(
      empresas =>{
        this.empresas = empresas;
      }
    );
  }

  updateUsuario():void{
    this.usuarioService.updateUsuario(this.usuario).subscribe(
        response => {
          Swal.fire('Actualizado', `Sus datos han sido actualizados correctamente`,'success');
          this.router.navigate(['/perfil']) //Para navegar cuando devuelve el objeto creado te redirige al menu
        }
    )
  }

  updatePassword():void{
    this.dtoPassword = new DtoPassword;
    this.dtoPassword.newPassword=this.newPassword;
    this.dtoPassword.oldPassword = this.oldPassword;

    this.usuarioService.updatePassword(this.usuario,this.dtoPassword).subscribe(
      response => {
        if(response){
          Swal.fire('Password','Actualizada correctamente, vuelva a logearse para continuar el servicio','success');
          this.authService.logout();
          this.router.navigate(['/index']);
        } else{
            Swal.fire('Error','Error al actualizar la password, compruebe que la password antigua es correcta', 'error');
          }
        }
      )
  }

  updateCompany():void{
    this.usuarioService.updateCompany(this.usuario,this.seleccionEmpresa).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
        Swal.fire('Empresa actualizada','Empresa actualizada, espere a ser aceptado por su nuevo jefe para poder logearse','success');
        this.authService.logout();
        this.router.navigate(['/index']);
      }, err => {
        if(err.status==400 || err.status==401){
          Swal.fire('Error al actualizar la empresa', 'Pruebe a volver a actualizarla', 'error');
        }
        }
      )
  }
}
