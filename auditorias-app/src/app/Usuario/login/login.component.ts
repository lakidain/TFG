import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2'
import { Usuario } from './usuario';
import { AuthService } from './auth.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-login',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit{

  usuario:Usuario;

  constructor(private authService: AuthService, private router: Router){ //Este metodo constructor inicializa de forma normal
    this.usuario= new Usuario(); //Y este tambien es valido, se puede hacer de las dos formas
  }

  ngOnInit(){
    if(this.authService.isAuthenticated()){
      Swal.fire('Login', 'Ya estás identificado', 'info')
      this.router.navigate(['/menu']);
    }
  }

  public login(): void {
    this.authService.login(this.usuario).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
        console.log(response);

        this.authService.guardarUsuario(response.access_token);
        this.authService.guardarToken(response.access_token);
        let usuario = this.authService.usuario;
        Swal.fire('Bienvenido al sistema', `Se ha logeado como ${usuario.username}`,'success');
        this.router.navigate(['/menu']);
      }, err => {
        if(err.status==400 || err.status==401){
          Swal.fire('Error al logearse', 'Pruebe a volver a introducir sus credenciales', 'error');
        }
      }
    )
  }
}
