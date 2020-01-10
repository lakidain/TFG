import { Component } from '@angular/core';
import { AuthService } from '../usuario/login/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent {
  title: string = 'Upaudit'

  constructor(private authService: AuthService, private router: Router) {

  }

  logout(): void {
    this.authService.logout();
    Swal.fire('Logout', 'Adios, esperemos que tenga un buen día', 'success');
    this.router.navigate(['/index']);
  }
}
