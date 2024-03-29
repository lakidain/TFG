import { BrowserModule, HammerGestureConfig, HAMMER_GESTURE_CONFIG } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';

/* Las aplicacion en angular son single page y hay que rutearles la url*/
import { RouterModule, Routes } from '@angular/router';

/*Para poder comunicarnos con el servidor necesitamos importar*/
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
//Cuando creas un componente es necesario importarlo aqui
import { LoginComponent } from './Usuario/login/login.component';
import { RegistroComponent } from './Usuario/registro/registro.component';
import { IndexComponent } from './index/index.component';
import { MenuComponent } from './menuPrincipal/menu.component';
import { ReportComponent } from './report/report.component';
import { AuditoriasComponent } from './audit/auditoria/auditorias.component';
import { CloseAuditComponent } from './audit/auditoria/closeAudit.component';
import { Questionnaire } from './audit/auditoria/questionnaire.component';
import { CitaCreationComponent } from './audit/cita/citaCreation.component';
import { CitaMostrarComponent } from './audit/cita/citaMostrar.component';
import { CitaModifyComponent } from './audit/cita/citaMostrarModals/modifyCita.component';
import { MessageModifyComponent } from './audit/cita/citaMostrarModals/modifyMessage.component';
import { EmployeeAddComponent } from './audit/cita/employeeAdd.component';
import { GestionPreguntas } from './audit/gestionActivos/gestionPreguntas.component';
import { AuditTypeModifyComponent } from './audit/gestionActivos/gestionPreguntasModals/modifyAuditType/modifyAuditType.component';
import { AuditThreatModifyComponent } from './audit/gestionActivos/gestionPreguntasModals/modifyAuditThreat/modifyAuditThreat.component';
import { AuditAssetModifyComponent } from './audit/gestionActivos/gestionPreguntasModals/modifyAuditAsset/modifyAuditAsset.component';
import { AuditVulnerabilityModifyComponent } from './audit/gestionActivos/gestionPreguntasModals/modifyAuditVulnerability/modifyAuditVulnerability.component';
import { AuditQuestionModifyComponent } from './audit/gestionActivos/gestionPreguntasModals/modifyAuditQuestion/modifyAuditQuestion.component';
import { AuditAnswerModifyComponent } from './audit/gestionActivos/gestionPreguntasModals/modifyAuditAnswer/modifyAuditAnswer.component';
import { GestionPersonalComponent } from './menuPrincipal/gestionPersonal.component';
import { CompanyToAuditComponent } from './menuPrincipal/companyToAudit/companyToAudit.component';
import { AuditModifyComponent } from './menuPrincipal/companyToAudit/modifyAudit.component';
import { PerfilUsuarioComponent } from './menuPrincipal/perfilUsuario.component';
import { CompanyModifyComponent } from './menuPrincipal/perfilUsuarioModals/modifyCompany.component';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';

/* DECLARACION DE SERVICIOS*/
import { UsuarioService } from './Usuario/login/usuario.service';
import { GestionPersonalService } from './menuPrincipal/gestionPersonal.service';
import { GestionPreguntasService } from './audit/gestionActivos/gestionPreguntas.service';
import { CompanyService } from './empresa/company.service';
import { PerfilUsuarioService } from './menuPrincipal/perfilUsuario.service';
import { AuditoriaService } from './audit/auditoria/auditorias.service';
import { CitaService } from './audit/cita/cita.service';

/* EXTRAS */
import { FullCalendarModule } from '@fullcalendar/angular'; // for FullCalendar!
import { NgxGalleryModule } from 'ngx-gallery'; //for gallery
import { NgxPaginationModule } from 'ngx-pagination'; //for pagination

export class CustomHammerConfig extends HammerGestureConfig {  /* Needed for Gallery */
  overrides = {
    pinch: { enable: false },
    rotate: { enable: false }
  };
}

const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' }, //Esta sera nuestra pagina principal
  { path: 'index', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'menu', component: MenuComponent },
  { path: 'report', component: ReportComponent },
  { path: 'cita', component: AuditoriasComponent },
  { path: 'gestionPreguntas', component: GestionPreguntas },
  { path: 'gestionPersonal', component: GestionPersonalComponent },
  { path: 'perfil', component: PerfilUsuarioComponent },
  { path: 'registro', component: RegistroComponent },
];

@NgModule({
  declarations: [
    LoginComponent,
    RegistroComponent,
    AppComponent,
    IndexComponent,
    HeaderComponent,
    NavbarComponent,
    MenuComponent,
    ReportComponent,
    AuditoriasComponent,
    CloseAuditComponent,
    Questionnaire,
    CitaCreationComponent,
    CitaMostrarComponent,
    MessageModifyComponent,
    CitaModifyComponent,
    EmployeeAddComponent,
    GestionPreguntas,
    AuditTypeModifyComponent,
    AuditThreatModifyComponent,
    AuditAssetModifyComponent,
    AuditVulnerabilityModifyComponent,
    AuditQuestionModifyComponent,
    AuditAnswerModifyComponent,
    GestionPersonalComponent,
    CompanyModifyComponent,
    AuditModifyComponent,
    CompanyToAuditComponent,
    PerfilUsuarioComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes), //Aqui le metemos el arreglo que creamos arriba que mapeaba
    NgxGalleryModule, //for Gallery
    FormsModule,
    FullCalendarModule, // for FullCalendar!
    NgxPaginationModule, //for Pagination
  ],
  providers: [UsuarioService, GestionPersonalService, GestionPreguntasService, CompanyService, PerfilUsuarioService, AuditoriaService, CitaService, DatePipe, { /* Needed for gallery */
    provide: HAMMER_GESTURE_CONFIG, useClass: CustomHammerConfig
  }], //LOS SERVICIOS DEBERAN SER COLOCADOS EN PROVIDERS
  bootstrap: [AppComponent]
})
export class AppModule { }
