import { Component } from '@angular/core';
import { AuthService } from '../usuario/login/auth.service';

@Component({
  selector: 'app-menu',
  /*template: ` CODIGO ` TAMBIEN podemos gacer templateUrl y le creamos una vista*/
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})

export class MenuComponent {

  constructor(private authService: AuthService){}
}
