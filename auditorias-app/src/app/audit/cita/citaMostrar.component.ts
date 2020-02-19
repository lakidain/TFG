import { Component, OnInit, Input } from '@angular/core';
import { ModalMostrarCita } from './modalMostrarCita.service';
import { Gallery } from './Gallery';
import { Usuario } from '../../usuario/login/usuario';
import { Cita } from './cita';
import { Message } from './message';
import { AuditoriasComponent } from '../auditoria/auditorias.component';

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

  constructor(private authService: AuthService, private modalMostrarCita: ModalMostrarCita, private citaService: CitaService, private auditoriasComponent: AuditoriasComponent) {
    this.usuario = authService.usuario;
    this.message = new Message();
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
        this.messagesList = response;
      }, err => {
        Swal.fire('Error', `Error al retirar los mensajes`, 'error');
      });
  }

  seleccionarFoto(event) {
    this.fotoSeleccionada = event.target.files[0];
    if (this.fotoSeleccionada.type.indexOf('image') < 0) { /* Si no se encuentra es un -1 */
      Swal.fire('Error seleccionar imagen: ', 'El archivo debe ser del tipo imagen', 'error');
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
      Swal.fire('Error Upload: ', 'Debe seleccionar una foto', 'error');
    } else {
      this.citaService.subirFoto(this.fotoSeleccionada, this.cita.id_appointment, this.textoEvidencia)
        .subscribe(mensaje => {
          Swal.fire('La foto se ha subido completamente', 'La foto se ha subido con exito', 'success');
          this.mostrarFotos();
          this.fotoSeleccionada = null;
          this.previewUrl = null;
          this.textoEvidencia = "";
        });
    }
  }

  mostrarFotos() {
    this.citaService.getFotos(this.cita.id_appointment)
      .subscribe(galeria => {
        this.gallery = galeria;
        if (galeria.length > 0) {
          this.galleryImages = galeria.map(foto => {
            return { small:"http://localhost:8080/api/uploads/img/"+foto.photo_gallery , medium: "http://localhost:8080/api/uploads/img/"+foto.photo_gallery, big: "http://localhost:8080/api/uploads/img/"+foto.photo_gallery, description: foto.description_gallery };
          });
        }
      });
  }

  cerrarCita(){
    if(confirm("¿Está seguro de cerrar la cita? Tras el cierre no se podrá añadir información adicional")){
      this.citaService.cerrarCita(this.cita.id_appointment).subscribe(
        response => {
          Swal.fire('Cita cerrada correctamente', 'La cita ha sido cerrada correctamente', 'success');
          this.auditoriasComponent.actualizarListaCitas();
          this.cerrarModal();
        }
      ), err => {
        if (err.status == 400 || err.status == 401) {
          Swal.fire('Error al cerrar la cita', 'Vuelva a intentarlo', 'error');
        }
      };
    }
  }

  cerrarModal() {
    this.modalMostrarCita.cerrarModal();
  }
}
