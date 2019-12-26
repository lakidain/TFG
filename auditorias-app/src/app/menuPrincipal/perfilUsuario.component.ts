import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'
import { AuthService } from '../usuario/login/auth.service';
import { Usuario } from '../usuario/login/usuario';
import { UsuarioService } from '../usuario/login/usuario.service'

@Component({
  selector: 'app-perfil',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './perfilUsuario.component.html',
  styleUrls: ['./perfilUsuario.component.css']
})

export class PerfilUsuarioComponent{

  usuario:Usuario;
  newPassword: String;
  oldPassword: String;

  constructor(private authService: AuthService, private usuarioService:UsuarioService, private router: Router){ //Este metodo constructor inicializa de forma normal
    this.usuario= authService.usuario; //Y este tambien es valido, se puede hacer de las dos formas
  }

  updateUsuario():void{
    this.usuarioService.updateUsuario(this.usuario).subscribe(
        response => {
          Swal.fire('Actualizado', `Sus datos han sido actualizados correctamente`,'success');
          this.router.navigate(['/perfil']) //Para navegar cuando devuelve el objeto creado te redirige al menu
        }
    )
  }

/*
  updatePassword():void{
    this.usuarioService.updatePassword(this.usuario, this.newPassword, this.oldPassword).subscribe(
        response => {
          this.authService.logout();
          Swal.fire('Password','Actualizada correctamente, vuelva a logearse para continuar el servicio','success');
          this.router.navigate(['/index']);
        }
    )
  } */
}
