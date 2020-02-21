package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.auditorias.springboot.backend.dto.DtoAuditEmployee;
import com.auditorias.springboot.backend.model.Audit_Employees;
import com.auditorias.springboot.backend.model.Role;
import com.auditorias.springboot.backend.model.Usuario;

@Mapper
public interface UsuarioMapper {

	@Select("select * from asi_users where id_company=#{id_company}")
	List<Usuario> findAllEmpleados(Long id_company);

	@Select("select * from asi_users where username=#{username}")
	List<Usuario> findByUsername(String username);
	
	@Select("select * from asi_users where id=#{id}")
	List<Usuario> getUsuario(Long id);

	@Select("select asi_rols.name from asi_users,asi_users_rols,asi_rols where asi_users.id=asi_users_rols.id_user"
			+ " and asi_users_rols.id_rol=asi_rols.id_rol and asi_users.username=#{username}")
	List<Role> findRole(String username);

	@Select("select id,name_user,username,email_user,phone_user,password,enabled,asi_users.id_company from asi_users,asi_companies where asi_users.id_company=asi_companies.id_company and asi_companies.name_company=#{name_company}")
	List<Usuario> checkCompany(String name_company);

	@Select("select password from asi_users where id=#{id}")
	List<Usuario> getOldPassword(Long id);

	@Insert("insert into asi_users(name_user,username,email_user,phone_user,password,enabled,id_company) values(#{name_user},#{username},#{email_user},#{phone_user},#{password},0,0)")
	void insert(Usuario user);

	@Update("update asi_users set email_user=#{email_user},phone_user=#{phone_user} where id=#{id}")
	void update(Usuario user);

	@Update("update asi_users set password=#{password} where id=#{id}")
	void updatePassword(String password, Long id);

	@Update("update asi_users set id_company=#{id_company},enabled=#{enabled} where id=#{id}")
	void updateCompany(Long id_company, Long id, boolean enabled);

	@Update("update asi_users set enabled=1 where id=#{id}")
	void enableUser(Usuario user);

	/* METODOS CREADOS PARA LOS CLIENTES */
	@Insert("insert into asi_users(name_user,username,email_user,phone_user,password,enabled,id_company) values(#{name_user},#{username},#{email_user},#{phone_user},#{password},1,#{id_company})")
	void insertCliente(Usuario user);

	@Insert("insert into asi_audit_employees(id_audit,id_user,appointment_permit_audit_employees,questionnaire_permit_audit_employees,report_permit_audit_employees) values(#{id_audit},#{id_user},0,0,0)")
	void associateClienteAuditoria(Long id_audit, Long id_user);

	@Select("select id,name_user,username,email_user,phone_user from asi_users,asi_audit,asi_companies where asi_audit.id_audit=#{id_audit} and asi_audit.id_company_audited=asi_companies.id_company and asi_companies.id_company=asi_users.id_company and asi_users.enabled=1")
	List<Usuario> getEmployees(Long id_audit);

	@Select("select id,id_audit_employees,id_audit,id_user,name_user,username,email_user,phone_user,appointment_permit_audit_employees,questionnaire_permit_audit_employees,report_permit_audit_employees from asi_users,asi_audit_employees where asi_users.enabled=1 and asi_users.id=asi_audit_employees.id_user and asi_audit_employees.id_audit=#{id_audit}")
	List<DtoAuditEmployee> getEmployeesAssociated(Long id_audit);

	@Select("select * from asi_audit_employees where id_audit=#{id_audit} and id_user=#{id_user}")
	List checkAssociation(Long id_audit, Long id_user);
	
	@Update("update asi_audit_employees set appointment_permit_audit_employees=#{appointment_permit_audit_employees},questionnaire_permit_audit_employees=#{questionnaire_permit_audit_employees},report_permit_audit_employees=#{report_permit_audit_employees} where id_audit_employees=#{id_audit_employees}")
	void updateEmployeesAssociated(Audit_Employees auditEmployees);
	
	@Delete("delete from asi_audit_employees where id_audit_employees=#{id_audit_employees}")
	void deleteEmployeeFromAppointment(Long id_audit_employees);
}
