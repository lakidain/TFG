import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2'
import { Usuario } from '../login/usuario';
import { UsuarioService } from '../login/usuario.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-registro',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})

export class RegistroComponent implements OnInit{

  private usuario: Usuario = new Usuario()

  constructor(private usuarioService: UsuarioService, private router: Router){} //Este metodo constructor inicializa de forma normal

  ngOnInit(){

  }

  public create(): void {
    this.usuarioService.create(this.usuario).subscribe(
        response => {
          Swal.fire('Registrado', `El registro ha sido un exito`,'success');
          this.router.navigate(['/login']) //Para navegar cuando devuelve el objeto creado te redirige al menu
        }
    )
  }

}
