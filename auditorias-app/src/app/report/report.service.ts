import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api

import { URL_BACKEND } from '../config/config';

@Injectable({
  providedIn: 'root'
})

export class ReportService {

  /* urlEndPoints*/
  private urlEndPointReportAuditorBoss: string = URL_BACKEND + '/api/reportAuditorBoss';
  private urlEndPointReportAuditedBoss: string = URL_BACKEND + '/api/reportAuditedBoss';
  private urlEndPointAuditorReports: string = URL_BACKEND + '/api/reportAuditor';
  private urlEndPointAuditedReports: string = URL_BACKEND + '/api/reportAudited';
  private urlEndPointDownloadReport: string = URL_BACKEND + '/api/report';

  constructor(private http: HttpClient) { }

  /* Get All company Reports if your Auditor Boss or Audited Boss*/
  getCompanyAuditorBoss(id_company: number): Observable<any> {
    return this.http.get<any>(this.urlEndPointReportAuditorBoss + "/" + id_company);
  }

  /* Get All company Reports if your Auditor Boss or Audited Boss*/
  getCompanyAuditedBoss(id_company: number): Observable<any> {
    return this.http.get<any>(this.urlEndPointReportAuditedBoss + "/" + id_company);
  }

  /* Get Reports Assigned to an auditor */
  getReportsAuditor(id: number): Observable<any> {
    return this.http.get<any>(this.urlEndPointAuditorReports + "/" + id);
  }

  /* Get Reports Assigned to an audited employee with permissions */
  getReportsAudited(id: number): Observable<any> {
    return this.http.get<any>(this.urlEndPointAuditedReports + "/" + id);
  }

  /* Download final report */
  downloadFile(id_report): Observable<any> {
    return this.http.get<any>(this.urlEndPointDownloadReport + "/" + id_report);
  }

}
