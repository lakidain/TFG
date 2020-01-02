import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient,HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
import { Injectable } from '@angular/core';

import { AuditAsset } from '../auditAsset';

@Injectable()
export class GestionPreguntasService {

  private urlEndPointAuditAssets: string = 'http://localhost:8080/api/assets';

  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  /* Returns existing assets */
  getAuditAssets(): Observable <AuditAsset[]>{
    return this.http.get<AuditAsset[]>(this.urlEndPointAuditAssets);
  }
}
