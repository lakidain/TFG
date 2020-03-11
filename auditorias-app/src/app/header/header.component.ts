import { Component } from '@angular/core';
import { AuthService } from '../usuario/login/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

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

  constructor(public authService: AuthService, public router: Router) {

  }

  logout(): void {
    this.authService.logout();
    Swal.fire('Logout', 'Goodbye, we hope you have a good day!', 'success');
    this.router.navigate(['/index']);
  }

  /* Hide Menu */
  hide() {
    navBarHide();
  }
}
