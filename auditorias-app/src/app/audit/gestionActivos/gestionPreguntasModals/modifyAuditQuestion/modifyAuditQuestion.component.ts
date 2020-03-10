import { Component, OnInit, Input } from '@angular/core';
import { AuditQuestion } from '../../../AuditQuestion';
import Swal from 'sweetalert2';

import { ModalModifyAuditQuestion } from './modalModifyAuditQuestion.service';
import { GestionPreguntas } from '../../gestionPreguntas.component';

@Component({
  selector: 'app-auditQuestionModify',
  templateUrl: './modifyAuditQuestion.component.html',
  styleUrls: ['./modifyAuditQuestion.component.css']
})

export class AuditQuestionModifyComponent {

  @Input() auditQuestion: AuditQuestion;

  constructor(private modalModifyAuditQuestion: ModalModifyAuditQuestion, private gestionPreguntas: GestionPreguntas) { }

  modificarAuditAsset() {
    this.modalModifyAuditQuestion.updateAuditQuestion(this.auditQuestion).subscribe(
      response => {
        Swal.fire('Question updated', 'Question correct update', 'success');
        this.cerrarModal();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating the question', 'Try again later', 'error');
        }
      }
    );
  }

  cerrarModal() {
    this.gestionPreguntas.updateQuestionsAndAnswers();
    this.gestionPreguntas.updateQuestionsRelatedToThreat();
    this.modalModifyAuditQuestion.cerrarModal();
  }
}
