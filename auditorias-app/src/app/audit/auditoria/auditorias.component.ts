import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Audit } from '../audit';
import { DtoAuditList } from '../../dto/dtoAuditList';
import { Usuario } from '../../usuario/login/usuario';
import { Cita } from '../cita/cita';
import { DtoAuditEmployee } from '../../dto/dtoAuditEmployee';
import { Audit_Employees } from '../auditEmployees';
import { AuditAsset } from '../auditAsset';

/* Services */
import { AuthService } from 'src/app/usuario/login/auth.service';
import { AuditoriaService } from './auditorias.service';
import { CitaService } from '../cita/cita.service';

/* Modals */
import { ModalCrearCita } from '../cita/modalCrearCita.service';
import { ModalEmployee } from '../cita/modalEmployee.service';
import { ModalMostrarCita } from '../cita//modalMostrarCita.service';
import { ModalQuestionnaire } from './modalQuestionnaire.service';
import { ModalCloseAudit } from './modalCloseAudit.service';

/* CALENDAR */
import { Calendar } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGrigPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction'; // for dateClick
import Swal from 'sweetalert2';

@Component({
  selector: 'app-citas',
  templateUrl: './auditorias.component.html',
  styleUrls: ['./auditorias.component.css']
})

export class AuditoriasComponent {

  auditList: DtoAuditList[];
  allAppointments: Cita[];
  auditAppointments: Cita[];
  usuario: Usuario;
  seleccionAudit: DtoAuditList;
  auditCitaCreation: DtoAuditList;
  auditEmployeeAdd: DtoAuditList;
  appointmentSelected: Cita;

  /* Questionnaire */
  assetSelection: AuditAsset;
  questionnaireList: AuditAsset[];


  /* Parametros para los empleados */
  auditEmployees: DtoAuditEmployee[];
  auditEmployee: Audit_Employees;

  /* Calendar inicializations*/
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin]; // important!
  calendarEvents = [
    //{ title: 'event 1', date: '2020-01-01' } EJEMPLO FORMATO
  ];

  /* Paginate inizialization */
  pageAppointment: number = 1;
  pageEmployees: number = 1;

  constructor(private authService: AuthService, private auditoriaService: AuditoriaService, private citaService: CitaService, private modalMostrarCita: ModalMostrarCita, private modalCrearCita: ModalCrearCita, private modalEmployee: ModalEmployee, private datePipe: DatePipe, private modalQuestionnaire:ModalQuestionnaire, private modalCloseAudit: ModalCloseAudit) {
    this.usuario = authService.usuario;
    this.auditEmployees = [];
    this.questionnaireList = [];
    this.auditEmployee = new Audit_Employees;
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
    this.actualizarCalendarioCitas();
    this.auditoriaService.getAuditsAssigned(this.usuario).subscribe(
      auditList => {
        this.auditList = auditList;
      }
    );
  }

  actualizarQuestionnaire() {
    this.auditoriaService.getAssets(this.seleccionAudit.id_audit).subscribe(
      assetList => {
        this.questionnaireList = assetList;
      }
    );
  }

  actualizarCalendarioCitas() {
    this.calendarEvents = [];
    this.citaService.getAppointmentList(this.usuario).subscribe(
      allAppointments => {
        if (allAppointments.length > 0) {
          this.calendarEvents = allAppointments.map(cita => {
            return { title: cita.name_appointment, date: this.transformDate(cita.date_appointment) };
          });
        }
      }
    );
  }

  actualizarEmployees() {
    this.auditoriaService.getEmployeesAssigned(this.seleccionAudit.id_audit).subscribe(
      allEmployees => {
        this.auditEmployee = new Audit_Employees;
        this.auditEmployees = allEmployees;
      }
    );
  }


  actualizarListaCitas() {
    this.auditAppointments = [];
    this.actualizarEmployees();
    this.actualizarQuestionnaire();
    this.citaService.getAuditAppointmentList(this.seleccionAudit).subscribe(
      allAppointments => {
        this.auditAppointments = allAppointments;
      }
    );
  }

  abrirModalAddAppointment() {
    this.auditCitaCreation = this.seleccionAudit;
    this.modalCrearCita.abrirModal();
  }

  abrirModalAsset(asset: AuditAsset){
    this.assetSelection = asset;
    this.modalQuestionnaire.abrirModal();
  }

  abrirModalAddEmployee() {
    this.auditEmployeeAdd = this.seleccionAudit;
    this.modalEmployee.abrirModal();
  }

  abrirModalCita(appointment: Cita) {
    this.appointmentSelected = appointment;
    this.modalMostrarCita.abrirModal();
  }

  abrirModalCloseAudit(){
    this.modalCloseAudit.abrirModal();
  }

  deleteAppointment(appointment: Cita){
    if(confirm("¿Está seguro de que desea eliminar la cita? Podrá perderse información de importancia para el proceso de auditoría")){
      this.auditoriaService.deleteAppointment(appointment.id_appointment).subscribe(
        reponse => {
          Swal.fire('Cita correctamente eliminada','','success');
          this.actualizarListaCitas();
        } , err => {
          if (err.status == 400 || err.status == 401) {
            Swal.fire('Error al eliminar la cita', 'Vuelva a intentarlo por favor', 'error');
          }
        }
      );
    }
  }

  transformDate(date) {
    return this.datePipe.transform(date, 'yyyy-MM-dd HH:mm', 'GMT');
  }

  /* Table employees methods*/
  activateAppointment(employee: DtoAuditEmployee) {
    this.auditEmployee.id_audit = employee.id_audit;
    this.auditEmployee.id_user = employee.id_user;
    this.auditEmployee.id_audit_employees = employee.id_audit_employees;
    this.auditEmployee.appointment_permit_audit_employees = 1;
    this.auditEmployee.questionnaire_permit_audit_employees = employee.questionnaire_permit_audit_employees;
    this.auditEmployee.report_permit_audit_employees = employee.report_permit_audit_employees;
    this.auditoriaService.updateEmployeesAssigned(this.auditEmployee).subscribe(
      response => {
        Swal.fire('Permisos actualizados', 'Permisos actualizados correctamente', 'success');
        this.actualizarEmployees();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error al actualizar permisos', 'Vuelva a intentarlo', 'error');
        }
      }
    );
  }

  activateQuestionnaire(employee: DtoAuditEmployee) {
    this.auditEmployee.id_audit = employee.id_audit;
    this.auditEmployee.id_user = employee.id_user;
    this.auditEmployee.id_audit_employees = employee.id_audit_employees;
    this.auditEmployee.appointment_permit_audit_employees = employee.appointment_permit_audit_employees;
    this.auditEmployee.questionnaire_permit_audit_employees = 1;
    this.auditEmployee.report_permit_audit_employees = employee.report_permit_audit_employees;
    this.auditoriaService.updateEmployeesAssigned(this.auditEmployee).subscribe(
      response => {
        Swal.fire('Permisos actualizados', 'Permisos actualizados correctamente', 'success');
        this.actualizarEmployees();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error al actualizar permisos', 'Vuela a intentarlo', 'error');
        }
      }
    );
  }

  activateReport(employee: DtoAuditEmployee) {
    this.auditEmployee.id_audit = employee.id_audit;
    this.auditEmployee.id_user = employee.id_user;
    this.auditEmployee.id_audit_employees = employee.id_audit_employees;
    this.auditEmployee.appointment_permit_audit_employees = employee.appointment_permit_audit_employees;
    this.auditEmployee.questionnaire_permit_audit_employees = employee.questionnaire_permit_audit_employees;
    this.auditEmployee.report_permit_audit_employees = 1;
    this.auditoriaService.updateEmployeesAssigned(this.auditEmployee).subscribe(
      response => {
        Swal.fire('Permisos actualizados', 'Permisos actualizados correctamente', 'success');
        this.actualizarEmployees();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error al actualizar permisos', 'Vuela a intentarlo', 'error');
        }
      }
    );
  }

  deActivateAppointment(employee: DtoAuditEmployee) {
    this.auditEmployee.id_audit = employee.id_audit;
    this.auditEmployee.id_user = employee.id_user;
    this.auditEmployee.id_audit_employees = employee.id_audit_employees;
    this.auditEmployee.appointment_permit_audit_employees = 0;
    this.auditEmployee.questionnaire_permit_audit_employees = employee.questionnaire_permit_audit_employees;
    this.auditEmployee.report_permit_audit_employees = employee.report_permit_audit_employees;
    this.auditoriaService.updateEmployeesAssigned(this.auditEmployee).subscribe(
      response => {
        Swal.fire('Permisos actualizados', 'Permisos actualizados correctamente', 'success');
        this.actualizarEmployees();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error al actualizar permisos', 'Vuela a intentarlo', 'error');
        }
      }
    );
  }

  deActivateQuestionnaire(employee: DtoAuditEmployee) {
    this.auditEmployee.id_audit = employee.id_audit;
    this.auditEmployee.id_user = employee.id_user;
    this.auditEmployee.id_audit_employees = employee.id_audit_employees;
    this.auditEmployee.appointment_permit_audit_employees = employee.appointment_permit_audit_employees;
    this.auditEmployee.questionnaire_permit_audit_employees = 0;
    this.auditEmployee.report_permit_audit_employees = employee.report_permit_audit_employees;
    this.auditoriaService.updateEmployeesAssigned(this.auditEmployee).subscribe(
      response => {
        Swal.fire('Permisos actualizados', 'Permisos actualizados correctamente', 'success');
        this.actualizarEmployees();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error al actualizar permisos', 'Vuela a intentarlo', 'error');
        }
      }
    );
  }

  deActivateReport(employee: DtoAuditEmployee) {
    this.auditEmployee.id_audit = employee.id_audit;
    this.auditEmployee.id_user = employee.id_user;
    this.auditEmployee.id_audit_employees = employee.id_audit_employees;
    this.auditEmployee.appointment_permit_audit_employees = employee.appointment_permit_audit_employees;
    this.auditEmployee.questionnaire_permit_audit_employees = employee.questionnaire_permit_audit_employees;
    this.auditEmployee.report_permit_audit_employees = 0;
    this.auditoriaService.updateEmployeesAssigned(this.auditEmployee).subscribe(
      response => {
        Swal.fire('Permisos actualizados', 'Permisos actualizados correctamente', 'success');
        this.actualizarEmployees();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error al actualizar permisos', 'Vuelva a intentarlo', 'error');
        }
      }
    );
  }

  deleteEmployeeFromAppointment(employee: DtoAuditEmployee){
    if(confirm("¿Está seguro de que desea eliminar la asociación?")){
      this.auditoriaService.deleteEmployeeFromAppointment(employee.id_audit_employees).subscribe(
        reponse => {
          Swal.fire('Asociación correctamente eliminada','','success');
          this.actualizarEmployees();
        } , err => {
          if (err.status == 400 || err.status == 401) {
            Swal.fire('Error al eliminar la asociación', 'Vuelva a intentarlo por favor', 'error');
          }
        }
      );
    }
  }

}
