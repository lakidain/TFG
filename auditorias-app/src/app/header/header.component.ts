import { Component } from '@angular/core';
import { AuthService } from '../Usuario/login/auth.service';

/* Javascript function import */
import '../../assets/javascript/navbarhide.js';

/* Navbar hiding control */
declare var navBarHide: Function;

@Component({
  selector: 'app-header',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent {
  title: string = 'Upaudit'

  constructor(public authService: AuthService) {

  }

  logout(): void {
    this.authService.logout();
  }

  /* Hide Menu */
  hide() {
    navBarHide();
  }
}
