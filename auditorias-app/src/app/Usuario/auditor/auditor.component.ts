import { Component, OnInit } from '@angular/core';
import {Auditor} from './auditor';
import {AuditorService} from './auditor.service';

@Component({
  selector: 'app-auditores',
  templateUrl: './auditor.component.html',
})
export class AuditoresComponent implements OnInit {

/*
  clientes: Cliente[] = [
    {id: 1, nombre: 'Ander',apellido: 'Lak',email: 'pepeyu@hotmail.com', createAt: '2017-08-11'},
    {id: 2, nombre: 'Paco',apellido: 'Pal',email: 'paco@hotmail.com', createAt: '2017-10-11'},
    {id: 3, nombre: 'Pepe',apellido: 'Pol',email: 'pepe@hotmail.com', createAt: '2017-12-09'},
    {id: 4, nombre: 'Alex',apellido: 'Pel',email: 'alex@hotmail.com', createAt: '2017-11-11'},
    {id: 5, nombre: 'Mongo',apellido: 'Pal',email: 'mongo@hotmail.com', createAt: '2017-12-12'},
  ];
*/

  auditores:Auditor[];
  private auditorService: AuditorService

  constructor(auditorService: AuditorService) {
    this.auditorService = auditorService;
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.auditorService.getAuditores().subscribe(
      auditores => this.auditores = auditores
    );
  }

}
