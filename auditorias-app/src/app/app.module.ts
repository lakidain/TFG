import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';

/* Las aplicacion en angular son single page y hay que rutearles la url*/
import { RouterModule, Routes } from '@angular/router';

/*Para poder comunicarnos con el servidor necesitamos importar*/
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
//Cuando creas un componente es necesario importarlo aqui
import { IndexComponent } from './index/index.component';
import { MenuComponent } from './menuPrincipal/menu.component';
import { AuditoriasComponent } from './audit/auditoria/auditorias.component';
import { CitaCreationComponent } from './audit/cita/citaCreation.component';
import { EmployeeAddComponent } from './audit/cita/employeeAdd.component';
import { GestionPreguntas } from './audit/gestionActivos/gestionPreguntas.component';
import { CompanyToAuditComponent } from './menuPrincipal/companyToAudit/companyToAudit.component';
import { GestionPersonalComponent } from './menuPrincipal/gestionPersonal.component';
import { PerfilUsuarioComponent } from './menuPrincipal/perfilUsuario.component';
import { LoginComponent } from './usuario/login/login.component';
import { RegistroComponent } from './usuario/registro/registro.component';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';

/* DECLARACION DE SERVICIOS*/
import { UsuarioService } from './usuario/login/usuario.service';
import { GestionPersonalService } from './menuPrincipal/gestionPersonal.service';
import { GestionPreguntasService } from './audit/gestionActivos/gestionPreguntas.service';
import { CompanyService } from './empresa/company.service';
import { PerfilUsuarioService } from './menuPrincipal/perfilUsuario.service';
import { AuditoriaService } from './audit/auditoria/auditorias.service';
import { CitaService } from './audit/cita/cita.service';

/* EXTRAS */
import { FullCalendarModule } from '@fullcalendar/angular'; // for FullCalendar!

const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' }, //Esta sera nuestra pagina principal
  { path: 'index', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'menu', component: MenuComponent },
  { path: 'cita', component: AuditoriasComponent },
  { path: 'gestionPreguntas', component: GestionPreguntas },
  { path: 'newCompany', component: CompanyToAuditComponent },
  { path: 'gestionPersonal', component: GestionPersonalComponent },
  { path: 'perfil', component: PerfilUsuarioComponent },
  { path: 'registro', component: RegistroComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    LoginComponent,
    RegistroComponent,
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    MenuComponent,
    AuditoriasComponent,
    CitaCreationComponent,
    EmployeeAddComponent,
    GestionPreguntas,
    GestionPersonalComponent,
    CompanyToAuditComponent,
    PerfilUsuarioComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes), //Aqui le metemos el arreglo que creamos arriba que mapeaba
    FormsModule,
    FullCalendarModule, // for FullCalendar!
  ],
  providers: [UsuarioService, GestionPersonalService, GestionPreguntasService, CompanyService, PerfilUsuarioService, AuditoriaService, CitaService, DatePipe], //LOS SERVICIOS DEBERAN SER COLOCADOS EN PROVIDERS
  bootstrap: [AppComponent]
})
export class AppModule { }
