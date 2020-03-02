import { Component, OnInit, Input } from '@angular/core';
import { ModalQuestionnaire } from './modalQuestionnaire.service';
import { Audit } from '../Audit';
import { AuditQuestion } from '../auditQuestion';
import { AuthService } from '../../usuario/login/auth.service';
import { AuditAsset } from '../auditAsset';
import { DtoQuestionnaire } from '../../dto/dtoQuestionnaire';
import { Usuario } from '../../usuario/login/usuario';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})

export class Questionnaire {

  @Input() asset: AuditAsset;
  @Input() audit: Audit;

  usuario: Usuario;

  /* Lista Questions */
  listaQuestions: AuditQuestion[] =[];

  /* Lista Answers */
  listaAnswers: DtoQuestionnaire[] =[];

  /* Respuestas */
  respuestas: DtoQuestionnaire[] = [];

  /* Respuestas existentes */
  answeredQuestionnaire: any[] = [];
  idAnswered: number[] = [];

  /* Lista con la relacion entre todas las tablas*/

  constructor(private authService: AuthService, private modalQuestionnaire: ModalQuestionnaire) {
    this.usuario = authService.usuario;
  }

  ngOnChanges(): void {
    this.updateListaQuestions();
    this.updateListaAnswers();
    this.updateAnsweredQuestionnaire();
    this.respuestas = [];
    this.answeredQuestionnaire = [];
    this.idAnswered = [];
  }

  updateListaQuestions() {
    this.modalQuestionnaire.getQuestions(this.asset.id_audit_asset).subscribe(
      assetQuestions => {
        this.listaQuestions = assetQuestions;
      }
    );
  }

  updateListaAnswers() {
    this.modalQuestionnaire.getAnswers(this.asset.id_audit_asset).subscribe(
      answers => {
        this.listaAnswers = answers;
      }
    );
  }

  updateAnsweredQuestionnaire() {
    this.modalQuestionnaire.getAnsweredQuestionnaire(this.audit.id_audit, this.usuario.id, this.asset.id_audit_asset).subscribe(
      answered => {
        this.answeredQuestionnaire = answered;
        for (var i = 0; i < this.answeredQuestionnaire.length; i++) {
          this.idAnswered[i] = this.answeredQuestionnaire[i].id_respuesta;
        }
      }
    );
  }

  saveChanges() {
    if (this.answeredQuestionnaire.length === 0) {
      this.modalQuestionnaire.postAnswers(this.respuestas).subscribe(
        answers => {
          Swal.fire('Success saving the form', 'Form has been completed', 'success');
          this.cerrarModal();
        }, err => {
          if (err.status == 400 || err.status == 401 || err.status == 500) {
            Swal.fire('Error saving form', 'Try again later', 'error');
          }
        }
      );
    } else{
      this.modalQuestionnaire.updateAnswers(this.respuestas).subscribe(
        answers => {
          Swal.fire('Success updating the form', 'Form has been updated', 'success');
          this.cerrarModal();
        }, err => {
          if (err.status == 400 || err.status == 401 || err.status == 500) {
            Swal.fire('Error updating form', 'Try again later', 'error');
          }
        }
      );
    }
  }

  cerrarModal() {
    this.updateAnsweredQuestionnaire();
    this.modalQuestionnaire.cerrarModal();
  }

  eligio(index, answer) {
    this.respuestas[index] = answer;
    this.respuestas[index]['id_asset'] = this.asset.id_audit_asset;
    this.respuestas[index]['id_audit'] = this.audit.id_audit;
    this.respuestas[index]['id_user'] = this.usuario.id;
  }

}
