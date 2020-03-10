import { Component, OnInit, Input } from '@angular/core';
import { Message } from '../message';
import Swal from 'sweetalert2';

import { CitaMostrarComponent } from '../citaMostrar.component';
import { ModalModifyMessage } from './modalModifyMessage.service';
import { AuditoriasComponent } from '../../auditoria/auditorias.component';

@Component({
  selector: 'app-messageModify',
  templateUrl: './modifyMessage.component.html',
  styleUrls: ['./modifyMessage.component.css']
})

export class MessageModifyComponent {

  @Input() message: Message;

  constructor(private modalModifyMessage: ModalModifyMessage, private auditoriasComponent: AuditoriasComponent, private citaMostrarComponent: CitaMostrarComponent) {
  }

  modificarMessage() {
    this.modalModifyMessage.updateMessage(this.message).subscribe(
      response => {
        Swal.fire('Message updated', 'Message updated correctly', 'success');
        this.cerrarModal();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error updating the message', 'Try again later', 'error');
        }
      }
    );
  }

  cerrarModal() {
    this.citaMostrarComponent.updateMessages();
    this.modalModifyMessage.cerrarModal();
  }
}
