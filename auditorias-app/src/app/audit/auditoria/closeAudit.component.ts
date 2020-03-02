import { Component, OnInit, Input } from '@angular/core';
import { Audit } from '../audit';
import Swal from 'sweetalert2';

/* Modals */
import { ModalCloseAudit } from './modalCloseAudit.service';

@Component({
  selector: 'app-closeAudit',
  templateUrl: './closeAudit.component.html',
  styleUrls: ['./closeAudit.component.css']
})

export class CloseAuditComponent {

  @Input() auditClose: Audit;

  /* Check if at least a questionnaire for each Asset has been completed */
  answered: boolean;

  /* Get a List with the Threats - Vulnerabilities associated to punctuate */
  threatsVulnerabilities: any[] = [];
  result: any[] = [];

  constructor(private modalCloseAudit: ModalCloseAudit) {
    this.auditClose = new Audit;
  }

  ngOnChanges(): void {
    this.checkAnsweredQuestionnaire();
    this.updateThreatsVulnerabilities();
  }

  updateThreatsVulnerabilities(){
    this.modalCloseAudit.updateThreatsVulnerabilities(this.auditClose.id_audit).subscribe(
      threatsAndVulnerabilities => {
        this.threatsVulnerabilities = threatsAndVulnerabilities;
        for(var i=0 ; i < this.threatsVulnerabilities.length; i++){
          this.threatsVulnerabilities[i]['disponibilidad'] = 0;
          this.threatsVulnerabilities[i]['confidencialidad'] = 0;
          this.threatsVulnerabilities[i]['integridad'] = 0;
          this.threatsVulnerabilities[i]['legalidad'] = 0;
          this.threatsVulnerabilities[i]['probabilidadAmenaza'] = 1;
        }
      }
    );
  }

  checkAnsweredQuestionnaire() {
    this.modalCloseAudit.checkAnsweredQuestionnaire(this.auditClose.id_audit).subscribe(
      answered => {
        this.answered = answered;
      }
    );
  }

  closeAudit(){
    console.log(this.threatsVulnerabilities);
  }

  cerrarModal() {
    this.modalCloseAudit.cerrarModal();
  }
}
