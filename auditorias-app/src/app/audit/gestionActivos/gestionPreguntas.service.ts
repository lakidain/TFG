import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
import { Injectable } from '@angular/core';

import { AuditAsset } from '../auditAsset';
import { AuditType } from '../auditType';
import { AuditThreat } from '../auditThreat';
import { DtoAssetCreation } from '../../dto/dtoAssetCreation';

@Injectable()
export class GestionPreguntasService {

  private urlEndPointAuditAssets: string = 'http://localhost:8080/api/assets';
  private urlEndPointCreateAuditType: string = 'http://localhost:8080/api/type';
  private urlEndPointAuditThreats: string = 'http://localhost:8080/api/threats';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  /* Returns existing assets */
  getAuditAssets(): Observable<AuditAsset[]> {
    return this.http.get<AuditAsset[]>(this.urlEndPointAuditAssets);
  }

  /* Returns existing threats */
  getAuditThreats(): Observable<AuditThreat[]> {
    return this.http.get<AuditThreat[]>(this.urlEndPointAuditThreats);
  }

  /* Creation Audit type */
  createAuditType(auditType: AuditType): Observable<any> {
    return this.http.post<any>(this.urlEndPointCreateAuditType, auditType, { headers: this.httpHeaders });
  }

  /* Creation Audit asset */
  createAsset(dtoAssetCreation: DtoAssetCreation): Observable<any> {
    return this.http.post<any>(this.urlEndPointAuditAssets, dtoAssetCreation, { headers: this.httpHeaders });
  }

  /* Creation Audit Threat */
  createThreat(newThreat: string, assetThreat, existingThreat): Observable<any>{
    let formData = new FormData();
    formData.append("newThreat",newThreat);
    formData.append("assetThreat",assetThreat); /* Al no tipar el dato podemos pasarlo como si fuera un String aunque sea un numero */
    formData.append("existingThreat",existingThreat);
    return this.http.post<any>(this.urlEndPointAuditThreats, formData);
  }
}
