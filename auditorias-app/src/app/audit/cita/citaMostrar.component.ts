import { Component, OnInit, Input } from '@angular/core';
import { ModalMostrarCita } from './modalMostrarCita.service';
import { Gallery } from './Gallery';
import { Usuario } from '../../usuario/login/usuario';
import { Cita } from './cita';
import { Message } from './message';

/* Servicios */
import { AuthService } from 'src/app/usuario/login/auth.service';
import { CitaService } from './cita.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-citaMostrar',
  templateUrl: './citaMostrar.component.html',
  styleUrls: ['./citaMostrar.component.css']
})

export class CitaMostrarComponent {

  @Input() cita: Cita;
  messagesList: Message[];
  message: Message;
  usuario: Usuario;
  gallery: Gallery;
  previewUrl:any = null;
  private fotoSeleccionada: File; /* Si no vamos a usar un atributo en la vista lo dejamos privado, si lo necesitamos en la vista publico*/

  constructor(private authService: AuthService, private modalMostrarCita: ModalMostrarCita, private citaService: CitaService) {
    this.usuario = authService.usuario;
    this.message = new Message();
  }

  ngOnChanges() { //Este componente es cuando se inicia el evento
    this.updateMessages();
  }

  createMessage() {
    this.message.id_appointment = this.cita.id_appointment;
    this.message.id_user = this.usuario.id;
    this.citaService.postMessage(this.message).subscribe(
      response => {
        Swal.fire('Mensaje Creado', `El mensaje ha sido creado`, 'success');
        this.message = new Message();
        this.updateMessages();
      }, err => {
        Swal.fire('Error', `Error al crear el mensaje, vuelva a intentarlo`, 'error');
      });
  }

  updateMessages() {
    this.citaService.getMessages(this.cita).subscribe(
      response => {
        console.log(response);
        this.messagesList = response;
      }, err => {
        Swal.fire('Error', `Error al retirar los mensajes`, 'error');
      });
  }

  seleccionarFoto(event) {
    this.fotoSeleccionada = event.target.files[0];
    console.log(this.fotoSeleccionada);
    if(this.fotoSeleccionada.type.indexOf('image') < 0) { /* Si no se encuentra es un -1 */
      Swal.fire('Error seleccionar imagen: ', 'El archivo debe ser del tipo imagen', 'error');
      this.fotoSeleccionada=null;
      this.previewUrl=null;
    }
    var reader = new FileReader();
    reader.readAsDataURL(this.fotoSeleccionada);
    reader.onload = (_event) => {
      this.previewUrl = reader.result;
    }
  }

  subirFoto() {
    if (!this.fotoSeleccionada) {
      Swal.fire('Error Upload: ', 'Debe seleccionar una foto', 'error');
    } else {
      this.citaService.subirFoto(this.fotoSeleccionada, this.cita.id_appointment)
        .subscribe(mensaje => {
          Swal.fire('La foto se ha subido completamente', 'La foto se ha subido con exito', 'success');
        });
    }
  }

  cerrarModal() {
    this.modalMostrarCita.cerrarModal();
  }
}
