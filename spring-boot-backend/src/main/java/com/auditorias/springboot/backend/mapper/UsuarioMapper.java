package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.auditorias.springboot.backend.model.Role;
import com.auditorias.springboot.backend.model.Usuario;

@Mapper
public interface UsuarioMapper {
	
	@Select("select * from asi_users where name_company=#{companyName}")
	List<Usuario> findAllEmpleados(String companyName);
	
	@Select("select * from asi_users where username=#{username}")
	List<Usuario> findByUsername(String username);
	
	@Select("select asi_rols.name from asi_users,asi_users_rols,asi_rols where asi_users.id=asi_users_rols.id_user"
			+ " and asi_users_rols.id_rol=asi_rols.id_rol and asi_users.username=#{username}")
	List<Role> findRole(String username);

	@Select("select id,name_user,username,email_user,phone_user,password,enabled,asi_users.name_company from asi_users,asi_companies where asi_users.name_company=asi_companies.name_company and asi_users.name_company=#{companyName}")
	List<Usuario> checkCompany(String companyName);

	@Insert("insert into asi_users(name_user,username,email_user,phone_user,password,enabled,name_company) values(#{name},#{username},#{email},#{phone},#{password},1,#{companyName})")
	void insert(Usuario user);

	@Insert("insert into asi_companies(name_company,id_user_boss) values(#{companyName},#{id})")
	void insertCompanyBoss(Long id, String companyName);
	
	@Update("update asi_users set email_user=#{email},phone_user=#{phone} where id=#{id}")
	void update(Usuario user);
	
	@Update("update asi_users set enabled=true where id=#{id}")
	void enableUser(Usuario user);
}
