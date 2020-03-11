import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'
import { Usuario } from '../usuario/login/usuario';
import { Empresa } from '../empresa/Empresa';
import { DtoPassword } from '../dto/dtoPassword';

import { PerfilUsuarioService } from './perfilUsuario.service';
import { UsuarioService } from '../usuario/login/usuario.service';
import { AuthService } from '../usuario/login/auth.service';

/* Modals */
import { ModalModifyCompany } from './perfilUsuarioModals/modalModifyCompany.service';

@Component({
  selector: 'app-perfil',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './perfilUsuario.component.html',
  styleUrls: ['./perfilUsuario.component.css']
})

export class PerfilUsuarioComponent {

  usuario: Usuario;
  empresas: Empresa[];

  /* Company of the worker */
  empresaModify: Empresa;

  /* Parameters for the password change */
  dtoPassword: DtoPassword;
  newPassword: string;
  oldPassword: string;

  /* Parameter for the company change */
  seleccionEmpresa: number;

  constructor(public authService: AuthService, public perfilUsuarioService: PerfilUsuarioService, public usuarioService: UsuarioService, public router: Router, public modalModifyCompany: ModalModifyCompany) { //Este metodo constructor inicializa de forma normal
    this.usuario = authService.usuario; //Y este tambien es valido, se puede hacer de las dos formas
    this.empresaModify = new Empresa;
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.userCompany();
    this.perfilUsuarioService.getEmpresas().subscribe(
      empresas => {
        this.empresas = empresas;
      }
    );
  }

  userCompany() {
    this.perfilUsuarioService.getCompany(this.usuario.id_company).subscribe(
      company => {
        this.empresaModify = company;
      }
    );
  }

  modifyCompany() {
    this.modalModifyCompany.abrirModal();
  }

  updateUsuario(): void {
    this.usuarioService.updateUsuario(this.usuario).subscribe(
      response => {
        this.authService.changeEmailAndPhone(this.usuario);
        Swal.fire('Updated', `Your data has been updated`, 'success');
        this.router.navigate(['/perfil']) //Para navegar cuando devuelve el objeto creado te redirige al menu
      }
    )
  }

  updatePassword(): void {
    this.dtoPassword = new DtoPassword;
    this.dtoPassword.newPassword = this.newPassword;
    this.dtoPassword.oldPassword = this.oldPassword;

    this.usuarioService.updatePassword(this.usuario, this.dtoPassword).subscribe(
      response => {
        if (response) {
          Swal.fire('Password updated', 'Log again to continue using the service', 'success');
          this.authService.logout();
          this.router.navigate(['/index']);
        } else {
          Swal.fire('Error', 'Error updating password, check both passwords are the same', 'error');
        }
      }
    )
  }

  updateCompany(): void {
    if (confirm("Are you sure you cant to change company? You will have to be accepted by your new boss, you'll lose acces until been accepted")) {
      this.usuarioService.updateCompany(this.usuario, this.seleccionEmpresa).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
        Swal.fire('Company updated', 'Wait to be accepted by your new boss', 'success');
        this.authService.logout();
        this.router.navigate(['/index']);
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating company', 'Try again later', 'error');
        }
      }
      )
    }
  }
}
