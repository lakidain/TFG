package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Cita;
import com.auditorias.springboot.backend.model.Gallery;

@Mapper
public interface CitaMapper {

	/*
	 * Insert new Appointment
	 */
	@Insert("insert into asi_appointment(id_appointment,id_audit,name_appointment,description_appointment,date_appointment,state_appointment) values (#{id_appointment},#{id_audit},#{name_appointment},#{description_appointment},#{date_appointment},0)")
	void insert(Cita cita);

	/*
	 * Returns all appointments for a concrete person
	 */
	@Select("select asi_appointment.id_appointment,asi_appointment.id_audit,asi_appointment.name_appointment,asi_appointment.description_appointment,asi_appointment.date_appointment,asi_appointment.state_appointment from asi_appointment,asi_audit where asi_appointment.id_audit=asi_audit.id_audit and asi_audit.id_user_manager=#{id}")
	List<Cita> getAllCitas(Long id);

	/*
	 * Returns a List of appointments associated with a concrete audit
	 */
	@Select("select asi_appointment.id_appointment,asi_appointment.id_audit,asi_appointment.name_appointment,asi_appointment.description_appointment,asi_appointment.date_appointment,asi_appointment.state_appointment from asi_appointment,asi_audit where asi_appointment.id_audit=asi_audit.id_audit and asi_audit.id_audit=#{id}")
	List<Cita> getAuditCitas(Long id);

	/*
	 * Uploads image related to an Appointment to Database
	 */
	@Insert("insert into asi_appointments_gallery(id_appointment,photo_gallery,description_gallery) values (#{id},#{imagen},#{description_gallery})")
	void uploadAppointmentImage(String imagen, Long id,String description_gallery);

	@Select("select * from asi_appointments_gallery where id_appointment=#{id}")
	List<Gallery> getGalleryCita(Long id);
}
