import { Component, OnInit, Input } from '@angular/core';
import { AuditAnswer } from '../../../AuditAnswer';
import Swal from 'sweetalert2';

import { ModalModifyAuditAnswer } from './modalModifyAnswer.service';
import { GestionPreguntas } from '../../gestionPreguntas.component';

@Component({
  selector: 'app-auditAnswerModify',
  templateUrl: './modifyAuditAnswer.component.html',
  styleUrls: ['./modifyAuditAnswer.component.css']
})

export class AuditAnswerModifyComponent {

  @Input() auditAnswer: AuditAnswer;

  constructor(private modalModifyAuditAnswer: ModalModifyAuditAnswer, private gestionPreguntas: GestionPreguntas) { }

  modificarAuditAnswer() {
    this.modalModifyAuditAnswer.updateAuditAnswer(this.auditAnswer).subscribe(
      response => {
        Swal.fire('Answer updated', 'Answer correct update', 'success');
        this.cerrarModal();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating the answer', 'Try again later', 'error');
        }
      }
    );
  }

  cerrarModal() {
    this.gestionPreguntas.updateAnswersRelatedToQuestion;
    this.gestionPreguntas.updateQuestionsAndAnswers;
    this.modalModifyAuditAnswer.cerrarModal();
  }
}
