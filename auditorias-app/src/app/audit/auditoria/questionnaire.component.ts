import { Component, OnInit, Input } from '@angular/core';
import { ModalQuestionnaire } from './modalQuestionnaire.service';
import { Audit } from '../Audit';
import { AuditThreat } from '../AuditThreat';
import { AuditVulnerability } from '../auditVulnerability';
import { AuditQuestion } from '../auditQuestion';
import { AuditAnswer } from '../auditAnswer';
import { AuditAsset } from '../auditAsset';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})

export class Questionnaire {

  @Input() asset: AuditAsset;
  @Input() audit: Audit;

  /* Lista Threats */
  listaThreats: AuditThreat[];

  /* Lista Vulnerabilities */
  listaVulnerabilities: AuditVulnerability[];

  /* Lista Questions */
  listaQuestions: AuditQuestion[];

  /* Lista Answers */
  listaAnswers: AuditAnswer[];

  /* Lista con la relacion entre todas las tablas*/

  constructor(private modalQuestionnaire: ModalQuestionnaire) {
  }

  ngOnChanges(): void {
    this.updateListaThreats();
    this.updateListaVulnerabilities();
    this.updateListaQuestions();
    this.updateListaAnswers();
  }

  updateListaThreats() {
    this.modalQuestionnaire.getThreats(this.asset.id_audit_asset).subscribe(
      assetThreats => {
        this.listaThreats = assetThreats;
      }
    );
  }

  updateListaVulnerabilities() {
    this.modalQuestionnaire.getVulnerabilities(this.asset.id_audit_asset).subscribe(
      assetVulnerabilities => {
        this.listaVulnerabilities = assetVulnerabilities;
      }
    );
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
      assetAnswers => {
        this.listaAnswers = assetAnswers;
      }
    );
  }

  cerrarModal() {
    this.modalQuestionnaire.cerrarModal();
  }

}
