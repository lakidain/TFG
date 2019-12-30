import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'
import { AuthService } from '../usuario/login/auth.service';
import { Usuario } from '../usuario/login/usuario';
import { UsuarioService } from '../usuario/login/usuario.service';
import { DtoPassword } from '../dto/dtoPassword';

@Component({
  selector: 'app-perfil',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './perfilUsuario.component.html',
  styleUrls: ['./perfilUsuario.component.css']
})

export class PerfilUsuarioComponent{

  usuario:Usuario;

  /* Parameters for the password change */
  dtoPassword: DtoPassword;
  newPassword: string;
  oldPassword: string;

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
}
