import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2'
import { DtoRegistro } from '../../dto/dtoRegistro';
import { UsuarioService } from '../login/usuario.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-registro',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})

export class RegistroComponent implements OnInit{

  private dtoRegistro: DtoRegistro = new DtoRegistro();

  constructor(private usuarioService: UsuarioService, private router: Router){ //Este metodo constructor inicializa de forma normal
  }

  ngOnInit(){
  }

  public create(): void {
    this.usuarioService.create(this.dtoRegistro).subscribe(
        response => {
          Swal.fire('Registrado', `El registro ha sido un exito`,'success');
          this.router.navigate(['/login']) //Para navegar cuando devuelve el objeto creado te redirige al menu
        }, err => {
          Swal.fire('Error', `El registro ha fallado, vuelva a intentarlo`,'error');
        }
    )
  }

}
