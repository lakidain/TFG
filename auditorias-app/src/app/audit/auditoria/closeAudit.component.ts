import { Component, OnInit, Input } from '@angular/core';
import { Audit } from '../audit';
import { AuditoriasComponent } from './auditorias.component';
import { DtoCloseAudit } from '../../dto/dtoCloseAudit';
import { Router } from '@angular/router'
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
  threatsVulnerabilities: DtoCloseAudit[] = [];

  /* Prepare info to recibe Answered Questions */
  answers: DtoCloseAudit[] = [];


  constructor(private modalCloseAudit: ModalCloseAudit, private auditoriasComponent: AuditoriasComponent, private router: Router) {
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
          this.threatsVulnerabilities[i]['availability_weight_audit_results'] = 0;
          this.threatsVulnerabilities[i]['confidentiality_weight_audit_results'] = 0;
          this.threatsVulnerabilities[i]['integrity_weight_audit_results'] = 0;
          this.threatsVulnerabilities[i]['legality_weight_audit_results'] = 0;
          this.threatsVulnerabilities[i]['audit_threat_probability_audit_results'] = 1;
          this.threatsVulnerabilities[i]['impact_level_audit_results'] = 1;
          this.threatsVulnerabilities[i]['recomendation_audit_results']='';
          this.threatsVulnerabilities[i]['id_audit']=this.auditClose.id_audit;
          this.updateAnswers();
        }
        console.log(this.threatsVulnerabilities);
      }
    );
  }

  /* Show the Auditor Answers for Questionnaires */
  updateAnswers(){
    /*
    this.modalCloseAudit.updateAnswers(this.threatsVulnerabilities).subscribe(
      answered => {
        this.answers = answered;
      }
    );*/
  }

  checkAnsweredQuestionnaire() {
    this.modalCloseAudit.checkAnsweredQuestionnaire(this.auditClose.id_audit).subscribe(
      answered => {
        this.answered = answered;
      }
    );
  }

  closeAudit(){
    this.modalCloseAudit.postResults(this.threatsVulnerabilities).subscribe(
      response => {
        Swal.fire('Auditoria cerrada', `El cierre de la auditoria ha sido un exito, el informe ha sido generado`, 'success');
        this.cerrarModal();
        this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
      }, err => {
        Swal.fire('Error', `La creacion ha fallado, vuelta a intentarlo`, 'error');
      }
    )
  }

  cerrarModal() {
    this.modalCloseAudit.cerrarModal();
  }
}
