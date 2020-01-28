package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Empresa;
import com.auditorias.springboot.backend.model.Message;

@Mapper
public interface MessageMapper {

	/*
	 * Insert new Message
	 */
	@Insert("insert into asi_messages(id_message,id_appointment,id_user,text_message) values(#{id_message},#{id_appointment},#{id_user},#{text_message})")
	void insert(Message message);
	
	/*
	 * Returns a List of all Messages from an appointment
	 */
	@Select("select * from asi_messages where id_appointment=#{id}")
	List<Message> getAppointmentMessages(Long id);
}
