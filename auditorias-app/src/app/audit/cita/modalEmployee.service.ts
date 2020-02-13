import { of, Observable } from 'rxjs'; //Podemos importar varias cosas a la vez
import { HttpClient, HttpHeaders } from '@angular/common/http'; //Necesitamos importar este paquete para conectarnos a la api
import { Usuario } from '../../usuario/login/usuario';
import { Injectable } from '@angular/core';
import { DtoRegistro } from '../../dto/dtoRegistro';

@Injectable({
  providedIn: 'root'
})
export class ModalEmployee {

  modal: boolean = false;
  private urlEndPointCreateEmployee: string = 'http://localhost:8080/api/clientes';
  private urlEndPointAssociateEmployee: string = 'http://localhost:8080/api/clientesAssociate';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { } //Definimos en el constructor el inyectable que vamos a usar para consumir el metodo get

  abrirModal(){
    this.modal = true;
  }

  cerrarModal() {
    this.modal = false;
  }

  getEmployees(id_audit:number):Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.urlEndPointCreateEmployee+"/"+id_audit);
  }

  createEmployee(dtoRegistro: DtoRegistro, companyAudited, id_audit):Observable<any>{
    let formData = new FormData();
    formData.append("email_user", dtoRegistro.email_user);
    formData.append("name_user", dtoRegistro.name_user);
    formData.append("password", dtoRegistro.password);
    formData.append("phone_user",dtoRegistro.phone_user);
    formData.append("username",dtoRegistro.username);
    formData.append("companyAudited",companyAudited);
    formData.append("id_audit",id_audit);
    return this.http.post<any>(this.urlEndPointCreateEmployee, formData);
  }

  associateEmployee(selectedEmployee,id_audit):Observable<any>{
    let formData= new FormData();
    formData.append("selectedEmployee",selectedEmployee);
    formData.append("id_audit",id_audit);
    return this.http.post<any>(this.urlEndPointAssociateEmployee, formData);
  }
}
