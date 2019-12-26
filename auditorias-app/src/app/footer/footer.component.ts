import {Component} from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})

export class FooterComponent {
  public autor: any = {nombre: 'Ander', apellido: 'Lakidain'}; //any es objetos que no sean de una clase particular, por defecto si no se define es public
}
