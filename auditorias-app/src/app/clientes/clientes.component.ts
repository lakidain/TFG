import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
})
export class ClientesComponent implements OnInit {

  /*
    clientes: Cliente[] = [
      {id: 1, nombre: 'Ander',apellido: 'Lak',email: 'pepeyu@hotmail.com', createAt: '2017-08-11'},
      {id: 2, nombre: 'Paco',apellido: 'Pal',email: 'paco@hotmail.com', createAt: '2017-10-11'},
      {id: 3, nombre: 'Pepe',apellido: 'Pol',email: 'pepe@hotmail.com', createAt: '2017-12-09'},
      {id: 4, nombre: 'Alex',apellido: 'Pel',email: 'alex@hotmail.com', createAt: '2017-11-11'},
      {id: 5, nombre: 'Mongo',apellido: 'Pal',email: 'mongo@hotmail.com', createAt: '2017-12-12'},
    ];
  */

  clientes: Cliente[];
  private clienteService: ClienteService

  constructor(clienteService: ClienteService) {
    this.clienteService = clienteService;
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.clienteService.getClientes().subscribe(
      clientes => this.clientes = clientes
    );
  }

}
