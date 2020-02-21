package com.auditorias.springboot.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auditorias.springboot.backend.mapper.MessageMapper;
import com.auditorias.springboot.backend.model.Cita;
import com.auditorias.springboot.backend.model.Message;

@CrossOrigin(origins = { "http://localhost:4200" }) // CrossOrigin es un porotocolo para comunicar peticiones que se
//realizan al navegador, desde aqui podemos controlar todo
//(metodos, direcciones)
@RestController // Como no va a tener vista
@RequestMapping("/api") // Aqui nos generara la url
public class MessageRestController {
	
	private MessageMapper messageMapper;

	public MessageRestController(MessageMapper messageMapper) {
		this.messageMapper = messageMapper;
	}
	
	@PostMapping("/message" )
	@ResponseStatus(HttpStatus.CREATED)
	public boolean create(@RequestBody Message message) {	//Como viene en formato JSON es necesario convertirlo	
		messageMapper.insert(message);	//Esto habria que revisarlo, parece que MYBATIS no puede devolver una clase
		return true;
	}
	
	/* Returns a List of all Messages from an appointment */
	@GetMapping("/message/{id}") // Para generar el endpoint
	public List<Message> getAppointmentMessages(@PathVariable Long id) {
		return messageMapper.getAppointmentMessages(id);
	}
	
	/* Modify a Message */
	@PutMapping("/message/{id}")
	public boolean updateMessage(@RequestBody Message message, @PathVariable Long id){
		messageMapper.updateMessage(message);
		return true;
	}
	
	/* Delete Message */
	@DeleteMapping("/message/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean deleteEmployeeFromAppointment(@PathVariable Long id) {
		messageMapper.deleteMessage(id);
		return true;
	}
	
}
