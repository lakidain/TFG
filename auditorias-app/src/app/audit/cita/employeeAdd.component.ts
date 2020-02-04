import { Component, OnInit, Input } from '@angular/core';
import { ModalEmployee } from './modalEmployee.service';
import { DtoRegistro } from '../../dto/dtoRegistro';
import { Audit } from '../audit';

@Component({
  selector: 'app-employeeAdd',
  templateUrl: './employeeAdd.component.html',
  styleUrls: ['./employeeAdd.component.css']
})

export class EmployeeAddComponent {

  @Input() audit: Audit;
  dtoRegistro: DtoRegistro;

  constructor(private modalEmployee:ModalEmployee) {
    this.dtoRegistro = new DtoRegistro();
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
  }

  cerrarModal(){
    this.modalEmployee.cerrarModal();
  }

  createEmployee(){
    console.log(this.dtoRegistro);
  }

  addEmployee(){

  }
}
