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
	
	@Select("select * from usuarios where company_name=#{companyName}")
	List<Usuario> findAllEmpleados(String companyName);
	
	@Select("select * from usuarios where username=#{username}")
	List<Usuario> findByUsername(String username);
	
	@Select("select roles.nombre from usuarios,usuarios_roles,roles where usuarios.id=usuarios_roles.usuario_id"
			+ " and usuarios_roles.role_id=roles.id and usuarios.username=#{username}")
	List<Role> findRole(String username);

	@Select("select id,name,username,email,phone,password,enabled,usuarios.company_name from usuarios,empresas where usuarios.company_name=empresas.company_name and usuarios.company_name=#{companyName}")
	List<Usuario> checkCompany(String companyName);

	@Insert("insert into usuarios(name,username,email,phone,password,enabled,company_name) values(#{name},#{username},#{email},#{phone},#{password},1,#{companyName})")
	void insert(Usuario user);

	@Insert("insert into empresas(company_name,company_boss) values(#{companyName},#{id})")
	void insertCompanyBoss(Long id, String companyName);
	
	@Update("update usuarios set email=#{email},phone=#{phone} where id=#{id}")
	void update(Usuario user);
}
