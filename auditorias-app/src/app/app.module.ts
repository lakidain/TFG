import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

/* Las aplicacion en angular son single page y hay que rutearles la url*/
import { RouterModule, Routes} from '@angular/router';

/*Para poder comunicarnos con el servidor necesitamos importar*/
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
//Cuando creas un componente es necesario importarlo aqui
import { IndexComponent } from './index/index.component';
import { MenuComponent } from './menuPrincipal/menu.component';
import { GestionPreguntas } from './audit/gestionActivos/gestionPreguntas.component';
import { GestionPersonalComponent } from './menuPrincipal/gestionPersonal.component';
import { PerfilUsuarioComponent } from './menuPrincipal/perfilUsuario.component';
import { LoginComponent } from './usuario/login/login.component';
import { RegistroComponent} from './usuario/registro/registro.component';
import { HeaderComponent } from './header/header.component';
import {FooterComponent} from './footer/footer.component';

/* DECLARACION DE SERVICIOS*/
import { UsuarioService } from './usuario/login/usuario.service';
import { GestionPersonalService } from './menuPrincipal/gestionPersonal.service';

const routes: Routes = [
  {path: '', redirectTo: '/index', pathMatch: 'full'}, //Esta sera nuestra pagina principal
  {path: 'index', component: IndexComponent},
  {path: 'login', component: LoginComponent},
  {path: 'menu', component: MenuComponent},
  {path: 'gestionPreguntas', component: GestionPreguntas},
  {path: 'gestionPersonal', component: GestionPersonalComponent},
  {path: 'perfil', component: PerfilUsuarioComponent},
  {path: 'registro', component: RegistroComponent},
  ];

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    LoginComponent,
    RegistroComponent,
    HeaderComponent,
    FooterComponent,
    MenuComponent,
    GestionPreguntas,
    GestionPersonalComponent,
    PerfilUsuarioComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes), //Aqui le metemos el arreglo que creamos arriba que mapeaba
    FormsModule,
  ],
  providers: [UsuarioService,GestionPersonalService], //LOS SERVICIOS DEBERAN SER COLOCADOS EN PROVIDERS
  bootstrap: [AppComponent]
})
export class AppModule { }
