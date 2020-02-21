import { Component, OnInit, Input } from '@angular/core';
import { ModalEmployee } from './modalEmployee.service';
import { AuditoriasComponent } from '../auditoria/auditorias.component';
import { DtoRegistro } from '../../dto/dtoRegistro';
import { Audit } from '../audit';
import { Usuario } from '../../Usuario/login/usuario';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-employeeAdd',
  templateUrl: './employeeAdd.component.html',
  styleUrls: ['./employeeAdd.component.css']
})

export class EmployeeAddComponent {

  @Input() audit: Audit;
  dtoRegistro: DtoRegistro;
  selectedEmployee: number;

  /* Employees of this company */
  employees: Usuario[];

  constructor(private modalEmployee:ModalEmployee,private auditoriasComponent : AuditoriasComponent) {
    this.dtoRegistro = new DtoRegistro();
  }

  ngOnChanges() { //Este componente es cuando se inicia el evento
    this.updateEmployees();
  }

  updateEmployees(){
    this.modalEmployee.getEmployees(this.audit.id_audit).subscribe(
      employees => {
        this.employees = employees;
      }
    );
  }

  cerrarModal(){
    this.modalEmployee.cerrarModal();
  }

  /* Primero crearemos el empleado, luego lo asociaremos con esta cita */
  createEmployee(){
    this.modalEmployee.createEmployee(this.dtoRegistro, this.audit.id_company_audited, this.audit.id_audit).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
      Swal.fire('Exito al crear el empleado', 'El empleado ha sido creado con exito', 'success');
      this.auditoriasComponent.actualizarEmployees();
      this.modalEmployee.cerrarModal();
    }, err => {
      if (err.status == 400 || err.status == 401 || err.status == 500) {
        Swal.fire('Error al crear el usuario', 'Vuelva a intentar crearlo o compruebe que no existe', 'error');
      }
    }
    );
  }

  addEmployee(){
    this.modalEmployee.associateEmployee(this.selectedEmployee,this.audit.id_audit).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
      Swal.fire('Exito al asociar el empleado', 'El empleado ha sido asociado con exito', 'success');
      this.auditoriasComponent.actualizarEmployees();
      this.modalEmployee.cerrarModal();
    }, err => {
      if (err.status == 400 || err.status == 401 || err.status == 500) {
        Swal.fire('Error al asociar el usuario', 'Compruebe que no está ya asociado', 'error');
      }
    }
    );
  }
}
