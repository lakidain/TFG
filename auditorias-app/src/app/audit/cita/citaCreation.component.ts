import { Component, OnInit, Input } from '@angular/core';
import { ModalCita } from './modalCita.service';
import { Audit } from '../audit';

@Component({
  selector: 'app-citaCreation',
  templateUrl: './citaCreation.component.html',
  styleUrls: ['./citaCreation.component.css']
})

export class CitaCreationComponent {

  @Input() audit: Audit;

  constructor(private modalCita:ModalCita) {
  }

  ngOnInit() { //Este componente es cuando se inicia el evento
  }

  create(){
      console.log("Creamoss");
  }

  cerrarModal(){
    this.modalCita.cerrarModal();
  }
}
