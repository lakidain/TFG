import { Component } from '@angular/core';
import { AuthService } from './usuario/login/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Bienvenido a Angular';

  curso: string = 'Curso Spring 5 con Angular 8';
  profesor: string = 'Ander';

  constructor(public authService: AuthService, public router: Router) {

  }
}
