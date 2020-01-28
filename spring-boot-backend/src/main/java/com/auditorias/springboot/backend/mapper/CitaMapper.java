package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Cita;

@Mapper
public interface CitaMapper {

	/*
	 * Insert new Appointment
	 */
	@Insert("insert into asi_appointment(id_appointment,id_audit,name_appointment,description_appointment,date_appointment) values(#{id_appointment},#{id_audit},#{name_appointment},#{description_appointment},#{date_appointment})")
	void insert(Cita cita);

	/*
	 * Returns all appointments for a concrete person
	 */
	@Select("select asi_appointment.id_appointment,asi_appointment.id_audit,asi_appointment.name_appointment,asi_appointment.description_appointment,asi_appointment.date_appointment from asi_appointment,asi_audit where asi_appointment.id_audit=asi_audit.id_audit and asi_audit.id_user_manager=#{id}")
	List<Cita> getAllCitas(Long id);
}
