package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.auditorias.springboot.backend.model.Role;

@Mapper
public interface RolesMapper {
	
	/*
	 * Create an Auditor Boss
	 */
	@Insert("insert into asi_users_rols(id_user,id_rol) values (#{id_user},#{rol_id})")
	void insertRole(Long id_user, Long rol_id);
	
	/*
	 * Select rol id
	 */
	@Select("select * from asi_rols where name=#{name}")
	List<Role> findRole(String name);

}
