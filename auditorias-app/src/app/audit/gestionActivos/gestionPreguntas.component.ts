import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'

import { DtoAssetCreation } from '../../dto/dtoAssetCreation';
import { AuditType } from '../auditType';
import { AuditAsset } from '../auditAsset';

import { GestionPersonalService } from '../../menuPrincipal/gestionPersonal.service';
import { GestionPreguntasService } from './gestionPreguntas.service';

@Component({
  selector: 'app-questions',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './gestionPreguntas.component.html',
  styleUrls: ['./gestionPreguntas.component.css']
})

export class GestionPreguntas {

  auditType: AuditType;
  auditAsset: AuditAsset;
  dtoAssetCreation: DtoAssetCreation;

  tipoAuditorias: AuditType[];
  assetsToAudit: AuditAsset[];
  answerNumber: string;

  constructor(private gestionPersonalService: GestionPersonalService, private gestionPreguntasService: GestionPreguntasService) {
    this.auditType = new AuditType();
    this.auditAsset = new AuditAsset();
    this.dtoAssetCreation = new DtoAssetCreation();
    this.answerNumber = "one";
  }

  ngOnInit() {
    this.updateAuditTypes();
    this.updateAuditAssets();
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
      types => {
        this.assetsToAudit = types;
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
    console.log(this.dtoAssetCreation.id_audit_type);
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

  /* Info events when info button clicked*/
  infoAuditType() {
    Swal.fire('Information', 'First create an Audit Type. Example: Security', 'info');
  }

  infoAsset() {
    Swal.fire('Information', 'Associate an audit type with an asset. This asset can be selected from a list of previously created assets or be created from the text field. Example: Security & Database', 'info');
  }

  infoAnswer() {
    Swal.fire('Information', 'A threat is associated with a question, which can be selected or created. At the same time, one question can have up to four answers associated with a score. This will help to calculate the Audit Score', 'info');
  }

  infoThreat(){
    Swal.fire('Information', 'An Asset have some threats Associated. Threat probability will be later filled. Example: User/Password have associated the thread Brute Force', 'info');
  }
}
