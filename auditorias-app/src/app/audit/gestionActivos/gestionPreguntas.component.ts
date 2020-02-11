import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'

import { DtoAssetCreation } from '../../dto/dtoAssetCreation';
import { AuditAsset } from '../auditAsset';
import { AuditType } from '../auditType';
import { AuditThreat } from '../auditThreat';
import { AuditVulnerability } from '../auditVulnerability';
import { AuditAnswer } from '../auditAnswer';
import { AuditQuestion } from '../auditQuestion';

import { GestionPersonalService } from '../../menuPrincipal/gestionPersonal.service';
import { GestionPreguntasService } from './gestionPreguntas.service';

@Component({
  selector: 'app-questions',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './gestionPreguntas.component.html',
  styleUrls: ['./gestionPreguntas.component.css']
})

export class GestionPreguntas {

  auditAsset: AuditAsset;
  answerNumber: string;
  /* Type Management */
  auditType: AuditType;
  tipoAuditorias: AuditType[];

  /* Assets Management */
  dtoAssetCreation: DtoAssetCreation;
  assetsToAudit: AuditAsset[];

  /* Threats Management */
  newThreat: string;
  assetThreat: number;
  existingThreat: number;
  threatsToAudit: AuditThreat[];

  /* Vulnerability Management */
  newVulnerability: string;
  threatVulnerability: number;
  existingVulnerability: number;
  vulnerabilitiesToAudit: AuditVulnerability[];

  /* Questions Management */
  vulnerabilityQuestion: number;
  newQuestion: string;
  newFirstAnswer: string;
  newSecondAnswer: string;
  newThirdAnswer: string;
  newFourthAnswer: string;
  newFifthAnswer: string;
  existingQuestion: number;
  existingNewFirstAnswer: number;
  existingNewSecondAnswer: number;
  existingNewThirdAnswer: number;
  existingNewFourthAnswer: number;
  existingNewFifthtAnswer: number;
  questionsToAudit: AuditQuestion[];
  answersToAudit: AuditAnswer[];

  constructor(private gestionPersonalService: GestionPersonalService, private gestionPreguntasService: GestionPreguntasService) {
    this.auditType = new AuditType();
    this.auditAsset = new AuditAsset();
    this.dtoAssetCreation = new DtoAssetCreation();
    this.answerNumber = "five";
    this.existingThreat = 0;
    this.existingVulnerability=0;
    this.existingQuestion=0;
    this.existingNewFirstAnswer=0;
    this.existingNewSecondAnswer=0;
    this.existingNewThirdAnswer=0;
    this.existingNewFourthAnswer=0;
    this.existingNewFifthtAnswer=0;
  }

  ngOnInit() {
    this.updateAuditTypes();
    this.updateAuditAssets();
    this.updateAuditThreats();
    this.updateVulnerabilities();
    this.updateQuestionsAndAnswers();
  }

  updateAuditTypes(): void {
    this.gestionPersonalService.getAuditTypes().subscribe(
      types => {
        this.tipoAuditorias = types;
      }
    );
  }

  updateAuditAssets(): void {
    this.gestionPreguntasService.getAuditAssets().subscribe(
      assets => {
        this.assetsToAudit = assets;
      }
    );
  }

  updateAuditThreats(): void{
    this.gestionPreguntasService.getAuditThreats().subscribe(
      threats => {
        this.threatsToAudit = threats;
      }
    );
  }

  updateVulnerabilities(): void{
    this.gestionPreguntasService.getAuditVulnerabilities().subscribe(
      vulnerabilities => {
        this.vulnerabilitiesToAudit = vulnerabilities;
      }
    );
  }

  updateQuestionsAndAnswers(): void{
    this.gestionPreguntasService.getAuditQuestions().subscribe(
      questions => {
        this.questionsToAudit = questions;
      }
    );
    this.gestionPreguntasService.getAuditAnswers().subscribe(
      answers => {
        this.answersToAudit = answers;
      }
    );
  }

  /* Creation of Audit types */
  typeSend() {
    this.gestionPreguntasService.createAuditType(this.auditType).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
      Swal.fire('Exito al crear el tipo de aditoria', 'El tipo de auditoria ha sido creado con exito', 'success');
      this.updateAuditTypes();
    }, err => {
      if (err.status == 400 || err.status == 401 || err.status == 500) {
        Swal.fire('Error al crear el tipo de auditoria', 'Vuelva a intentar crearla o compruebe que no existe', 'error');
      }
    }
    );
  }

  /* Creation of Assets */
  assetSend() {
    if (!this.dtoAssetCreation.name_audit_asset) {
      this.dtoAssetCreation.name_audit_asset = "";
    }
    this.gestionPreguntasService.createAsset(this.dtoAssetCreation).subscribe(response => { //this.router.navigate(['/menu']) //Para navegar cuando devuelve el objeto creado te redirige al menu
      Swal.fire('Exito al crear el activo', 'El activo ha sido creado con exito', 'success');
      this.updateAuditAssets();
    }, err => {
      if (err.status == 400 || err.status == 401 || err.status == 500) {
        Swal.fire('Error al crear el tipo de activo', 'Vuelva a intentar crearlo o compruebe que no existe', 'error');
      }
    }
    );
  }

  /* Creation of threats */
  threatSend(){
    if (!this.newThreat) {
      this.newThreat = "";
    }
    this.gestionPreguntasService.createThreat(this.newThreat, this.assetThreat, this.existingThreat).subscribe(response => {
      Swal.fire('Exito al crear la amenaza', 'La amenaza ha sido creada con exito', 'success');
      this.updateAuditThreats();
    }, err => {
      if (err.status == 400 || err.status == 401 || err.status == 500) {
        Swal.fire('Error al crear la amenaza', 'Vuelva a a crearla', 'error');
      }
    }
    );
  }

  /* Creation of vulnerability */
  vulnerabilitySend(){
    if (!this.newVulnerability) {
      this.newVulnerability = "";
    }
    this.gestionPreguntasService.createVulnerability(this.newVulnerability, this.threatVulnerability, this.existingVulnerability).subscribe(response => {
      Swal.fire('Exito al crear la vulnerabilidad', 'La vulnerabilidad ha sido creada con exito', 'success');
      this.updateVulnerabilities();
    }, err => {
      if (err.status == 400 || err.status == 401 || err.status == 500) {
        Swal.fire('Error al crear la vulnerabilidad', 'Vuelva a a crearla', 'error');
      }
    }
    );
  }

  /* Creation of a question with answers */
  questionSend(){
    if (!this.newQuestion) {
      this.newQuestion = "";
    }
    if (!this.newFirstAnswer) {
      this.newFirstAnswer = "";
    }
    if (!this.newSecondAnswer) {
      this.newSecondAnswer = "";
    }
    if (!this.newThirdAnswer) {
      this.newThirdAnswer = "";
    }
    if (!this.newFourthAnswer) {
      this.newFourthAnswer = "";
    }
    if (!this.newFifthAnswer) {
      this.newFifthAnswer = "";
    }
    this.gestionPreguntasService.createQuestion(this.newQuestion, this.newFirstAnswer, this.newSecondAnswer, this.newThirdAnswer, this.newFourthAnswer,this.newFifthAnswer,this.vulnerabilityQuestion, this.existingQuestion,
      this.existingNewFirstAnswer, this.existingNewSecondAnswer, this.existingNewThirdAnswer, this.existingNewFourthAnswer, this.existingNewFifthtAnswer).subscribe(response => {
      Swal.fire('Exito al crear la pregunta', 'La pregunta ha sido creada con exito', 'success');
      this.updateQuestionsAndAnswers();
    }, err => {
      if (err.status == 400 || err.status == 401 || err.status == 500) {
        Swal.fire('Error al crear la pregunta', 'Vuelva a a crearla', 'error');
      }
    }
    );
  }

  /* Info events when info button clicked*/
  infoAuditType() {
    Swal.fire('Information', 'First create an Audit Type. Example: Security', 'info');
  }

  infoAsset() {
    Swal.fire('Information', 'Associate an audit type with an asset. This asset can be selected from a list of previously created assets or be created from the text field. Example: Security & Database', 'info');
  }

  infoAnswer() {
    Swal.fire('Information', 'A vulnerability is associated with a question, which can be selected or created. At the same time, one question can have up to four answers associated with a score. This will help to calculate the Audit Score', 'info');
  }

  infoThreat(){
    Swal.fire('Information', 'An Asset have some threats Associated. Threat probability will be later filled. Example: User/Password have associated the thread Brute Force', 'info');
  }

  infoVulnerability(){
    Swal.fire('Information', 'A threat have some vulnerabilities associated. As an example a logic intrusion could be done by an outdated DB or by some options bad configurated', 'info');
  }
}
