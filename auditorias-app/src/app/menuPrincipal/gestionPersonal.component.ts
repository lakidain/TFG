import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../usuario/login/auth.service';
import { Usuario } from '../usuario/login/usuario';
import { Empresa } from '../empresa/empresa';

import { GestionPersonalService } from './gestionPersonal.service';

@Component({
  selector: 'app-perfil',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './gestionPersonal.component.html',
  styleUrls: ['./gestionPersonal.component.css']
})

export class GestionPersonalComponent{

  empleados:Usuario[];
  empresas:Empresa[];
  usuario:Usuario;
  seleccionUsuario:number;
  seleccionEmpresa:String;

  constructor(private authService: AuthService, private gestionPersonalService:GestionPersonalService,private router: Router){ //Este metodo constructor inicializa de forma normal
    this.usuario= authService.usuario; //Y este tambien es valido, se puede hacer de las dos formas
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.gestionPersonalService.getEmpleados(this.usuario).subscribe(
      empleados =>{
        this.empleados = empleados;
      }
    );
    this.gestionPersonalService.getEmpresas().subscribe(
      empresas =>{
        this.empresas = empresas;
      }
    );
  }
  enviar():void{
    console.log(this.seleccionUsuario);
  }
}
