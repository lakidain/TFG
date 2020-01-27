import { Component, OnInit, Input } from '@angular/core';
import { ModalEmployee } from './modalEmployee.service';
import { Audit } from '../audit';

@Component({
  selector: 'app-employeeAdd',
  templateUrl: './employeeAdd.component.html',
  styleUrls: ['./employeeAdd.component.css']
})

export class EmployeeAddComponent {

  @Input() audit: Audit;

  constructor(private modalEmployee:ModalEmployee) {
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
  }

  cerrarModal(){
    this.modalEmployee.cerrarModal();
  }
}
