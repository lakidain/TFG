import { Component } from '@angular/core';
import { Router } from '@angular/router';

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

export class GestionPreguntas{

  auditType: AuditType;
  auditAsset: AuditAsset;

  tipoAuditorias:AuditType[];
  assetsToAudit: AuditAsset[];

  constructor(private gestionPersonalService:GestionPersonalService, private gestionPreguntasService:GestionPreguntasService){
    this.auditType = new AuditType();
    this.auditAsset = new AuditAsset();
  }

  ngOnInit(){
    this.updateAuditTypes();
    this.updateAuditAssets();
  }

  updateAuditTypes():void{
    this.gestionPersonalService.getAuditTypes().subscribe(
      types=>{
        this.tipoAuditorias=types;
      }
    );
  }

  updateAuditAssets():void{
    this.gestionPreguntasService.getAuditAssets().subscribe(
      types=>{
        this.assetsToAudit=types;
      }
    );
  }

  /* Creation of Audit types */
  typeSend(){
    console.log(this.auditType.name_audit_type);
  }

  /* Creation of Assets */
  assetSend(){
    if(!this.auditAsset.name_audit_asset){  //If the text field it's not completed then we use the select
      console.log(this.auditType.id_audit_type);
    } else {
      console.log(this.auditAsset.name_audit_asset);
    }
  }
}
