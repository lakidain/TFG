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

	/**
	 * Find company employees
	 */
	@Select("select * from asi_users where id_company=#{id_company}")
	List<Usuario> findAllEmpleados(Long id_company);

	/**
	 * Returns a user by his username
	 */
	@Select("select * from asi_users where username=#{username}")
	List<Usuario> findByUsername(String username);

	/**
	 * Returns a user by his id
	 */
	@Select("select * from asi_users where id=#{id}")
	List<Usuario> getUsuario(Long id);

	/**
	 * Returns user rol by username
	 */
	@Select("select asi_rols.name from asi_users,asi_users_rols,asi_rols where asi_users.id=asi_users_rols.id_user"
			+ " and asi_users_rols.id_rol=asi_rols.id_rol and asi_users.username=#{username}")
	List<Role> findRole(String username);

	/**
	 * Returns a users from a company
	 */
	@Select("select id,name_user,username,email_user,phone_user,password,enabled,asi_users.id_company from asi_users,asi_companies where asi_users.id_company=asi_companies.id_company and asi_companies.name_company=#{name_company}")
	List<Usuario> checkCompany(String name_company);

	/**
	 * Get old password from a user
	 */
	@Select("select password from asi_users where id=#{id}")
	List<Usuario> getOldPassword(Long id);

	/**
	 * Create a user
	 */
	@Insert("insert into asi_users(name_user,username,email_user,phone_user,password,enabled,id_company) values(#{name_user},#{username},#{email_user},#{phone_user},#{password},0,0)")
	void insert(Usuario user);

	/**
	 * Update a user
	 */
	@Update("update asi_users set email_user=#{email_user},phone_user=#{phone_user} where id=#{id}")
	void update(Usuario user);

	/**
	 * Update user password
	 */
	@Update("update asi_users set password=#{password} where id=#{id}")
	void updatePassword(String password, Long id);

	/**
	 * Update user company
	 */
	@Update("update asi_users set id_company=#{id_company},enabled=#{enabled} where id=#{id}")
	void updateCompany(Long id_company, Long id, boolean enabled);

	/**
	 * Update user state
	 */
	@Update("update asi_users set enabled=1 where id=#{id}")
	void enableUser(Usuario user);

	/* METODOS CREADOS PARA LOS CLIENTES */
	/**
	 * Create a client
	 */
	@Insert("insert into asi_users(name_user,username,email_user,phone_user,password,enabled,id_company) values(#{name_user},#{username},#{email_user},#{phone_user},#{password},1,#{id_company})")
	void insertCliente(Usuario user);

	/**
	 * Associate a client with an audit
	 */
	@Insert("insert into asi_audit_employees(id_audit,id_user,appointment_permit_audit_employees,questionnaire_permit_audit_employees,report_permit_audit_employees) values(#{id_audit},#{id_user},0,0,0)")
	void associateClienteAuditoria(Long id_audit, Long id_user);

	/**
	 * Get users from a company participating on an audit
	 */
	@Select("select id,name_user,username,email_user,phone_user from asi_users,asi_audit,asi_companies where asi_audit.id_audit=#{id_audit} and asi_audit.id_company_audited=asi_companies.id_company and asi_companies.id_company=asi_users.id_company and asi_users.enabled=1")
	List<Usuario> getEmployees(Long id_audit);

	/**
	 * Get clients participating on an audit
	 */
	@Select("select id,id_audit_employees,id_audit,id_user,name_user,username,email_user,phone_user,appointment_permit_audit_employees,questionnaire_permit_audit_employees,report_permit_audit_employees from asi_users,asi_audit_employees where asi_users.enabled=1 and asi_users.id=asi_audit_employees.id_user and asi_audit_employees.id_audit=#{id_audit}")
	List<DtoAuditEmployee> getEmployeesAssociated(Long id_audit);

	/**
	 * Returns a client associated with an audit
	 */
	@Select("select * from asi_audit_employees where id_audit=#{id_audit} and id_user=#{id_user}")
	List<Audit_Employees> checkAssociation(Long id_audit, Long id_user);

	/**
	 * Update permits for a client in an audit
	 */
	@Update("update asi_audit_employees set appointment_permit_audit_employees=#{appointment_permit_audit_employees},questionnaire_permit_audit_employees=#{questionnaire_permit_audit_employees},report_permit_audit_employees=#{report_permit_audit_employees} where id_audit_employees=#{id_audit_employees}")
	void updateEmployeesAssociated(Audit_Employees auditEmployees);

	/**
	 * Delete a client from an audit process
	 */
	@Delete("delete from asi_audit_employees where id_audit_employees=#{id_audit_employees}")
	void deleteEmployeeFromAppointment(Long id_audit_employees);
}
