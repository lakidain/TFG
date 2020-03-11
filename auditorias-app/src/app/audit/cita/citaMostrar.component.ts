import { Component, OnInit, Input } from '@angular/core';
import { ModalMostrarCita } from './modalMostrarCita.service';
import { Gallery } from './Gallery';
import { Usuario } from '../../usuario/login/usuario';
import { Cita } from './cita';
import { Message } from './message';
import { AuditoriasComponent } from '../auditoria/auditorias.component';

/* Modals */
import { ModalModifyCita } from './citaMostrarModals/modalModifyCita.service';
import { ModalModifyMessage } from './citaMostrarModals/modalModifyMessage.service';

/* Servicios */
import { AuthService } from 'src/app/usuario/login/auth.service';
import { CitaService } from './cita.service';
import Swal from 'sweetalert2';

/* Gallery */
import { NgxGalleryOptions, NgxGalleryImage, NgxGalleryAnimation } from 'ngx-gallery';

@Component({
  selector: 'app-citaMostrar',
  templateUrl: './citaMostrar.component.html',
  styleUrls: ['./citaMostrar.component.css']
})

export class CitaMostrarComponent implements OnInit {

  @Input() cita: Cita;
  messagesList: Message[];
  message: Message;
  usuario: Usuario;
  gallery: Gallery[];
  textoEvidencia: string;
  previewUrl: any = null;
  private fotoSeleccionada: File; /* Si no vamos a usar un atributo en la vista lo dejamos privado, si lo necesitamos en la vista publico*/

  /* Gallery config */
  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[];

  /* Message Modify Parameter */
  messageClicked: Message;

  /* Pagination */
  p: number = 1;

  constructor(public authService: AuthService, public modalMostrarCita: ModalMostrarCita, public citaService: CitaService, public auditoriasComponent: AuditoriasComponent, public modalModifyCita: ModalModifyCita,
    public modalModifyMessage: ModalModifyMessage) {
    this.usuario = authService.usuario;
    this.message = new Message();
    this.messageClicked = new Message();
    this.messagesList = [];
  }

  ngOnInit(): void {

    this.galleryOptions = [
      {
        imageDescription: true,
        width: '600px',
        height: '400px',
        thumbnailsColumns: 4,
        imageAnimation: NgxGalleryAnimation.Slide
      },
      // max-width 800
      {
        breakpoint: 800,
        width: '100%',
        height: '600px',
        imagePercent: 80,
        thumbnailsPercent: 20,
        thumbnailsMargin: 20,
        thumbnailMargin: 20
      },
      // max-width 400
      {
        breakpoint: 400,
        preview: false,
      }
    ];
    this.galleryImages = []
    /*
        this.galleryImages = [
          {
            small: 'assets/1-small.jpg',
            medium: 'assets/1-medium.jpg',
            big: 'assets/1-big.jpg'
          },
          {
            small: 'assets/2-small.jpg',
            medium: 'assets/2-medium.jpg',
            big: 'assets/2-big.jpg'
          },
          {
            small: 'assets/3-small.jpg',
            medium: 'assets/3-medium.jpg',
            big: 'assets/3-big.jpg'
          }
        ];
        */
  }

  ngOnChanges() { //Este componente es cuando se inicia el evento
    this.updateMessages();
    this.mostrarFotos();
    this.p = 1;
  }

  modifyAppointment() {
    this.modalModifyCita.abrirModal();
  }

  createMessage() {
    this.message.id_appointment = this.cita.id_appointment;
    this.message.id_user = this.usuario.id;
    this.citaService.postMessage(this.message).subscribe(
      response => {
        Swal.fire('Message Created', `Message has been created`, 'success');
        this.message = new Message();
        this.updateMessages();
      }, err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error', `Error creating the message, try again later`, 'error');
        }
      });
  }

  deleteMessage(message: Message) {
    if (confirm("Are you sure you want to remove the message?")) {
      this.citaService.deleteMessage(message.id_message).subscribe(
        response => {
          Swal.fire('Message Removed', `The message has been removed successfully`, 'success');
          this.message = new Message();
          this.updateMessages();
        }, err => {
          if (err.status == 400 || err.status == 401) {
            Swal.fire('Error', `Error removing the message, try again later`, 'error');
          }
        });
    }
  }

  modifyMessage(message: Message) {
    this.messageClicked = message;
    this.modalModifyMessage.abrirModal();
  }

  updateMessages() {
    this.citaService.getMessages(this.cita).subscribe(
      response => {
        this.messagesList = response;
      }, err => {
        Swal.fire('Error', `Error updating messages`, 'error');
      });
  }

  seleccionarFoto(event) {
    this.fotoSeleccionada = event.target.files[0];
    if (this.fotoSeleccionada.type.indexOf('image') < 0) { /* Si no se encuentra es un -1 */
      Swal.fire('Error selecting image: ', 'File must be image type', 'error');
      this.fotoSeleccionada = null;
      this.previewUrl = null;
    }
    var reader = new FileReader();
    reader.readAsDataURL(this.fotoSeleccionada);
    reader.onload = (_event) => {
      this.previewUrl = reader.result;
    }
  }

  subirFoto() {
    if (!this.fotoSeleccionada) {
      Swal.fire('Error Upload: ', 'You should select a photo', 'error');
    } else {
      this.citaService.subirFoto(this.fotoSeleccionada, this.cita.id_appointment, this.textoEvidencia)
        .subscribe(mensaje => {
          Swal.fire('Photo has been uploaded', 'Photo succesfully updated', 'success');
          this.mostrarFotos();
          this.fotoSeleccionada = null;
          this.previewUrl = null;
          this.textoEvidencia = "";
        }, error => {
          Swal.fire('Photo hasn\'t been uploaded', 'Photo failed to load, maximum size allowed 10MB', 'error');
        }
        );
    }
  }

  mostrarFotos() {
    this.citaService.getFotos(this.cita.id_appointment)
      .subscribe(galeria => {
        this.gallery = galeria;
        if (galeria.length > 0) {
          this.galleryImages = galeria.map(foto => {
            return { small: "http://localhost:8080/api/uploads/img/" + foto.photo_gallery, medium: "http://localhost:8080/api/uploads/img/" + foto.photo_gallery, big: "http://localhost:8080/api/uploads/img/" + foto.photo_gallery, description: foto.description_gallery };
          });
        }
      });
  }

  cerrarCita() {
    if (confirm("Are you sure you want to close Appointment? You won't be able to add information")) {
      this.citaService.cerrarCita(this.cita.id_appointment).subscribe(
        response => {
          Swal.fire('Appointment close', 'Appointment successfully closed', 'success');
          this.auditoriasComponent.actualizarListaCitas();
          this.cerrarModal();
        }
      ), err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error closing appointment', 'Try again later', 'error');
        }
      };
    }
  }

  cerrarModal() {
    this.modalMostrarCita.cerrarModal();
  }
}
