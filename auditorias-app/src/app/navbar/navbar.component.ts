import { Component } from '@angular/core';
import { AuthService } from '../usuario/login/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent {
  constructor(public authService: AuthService, public router: Router) {

  }
}
