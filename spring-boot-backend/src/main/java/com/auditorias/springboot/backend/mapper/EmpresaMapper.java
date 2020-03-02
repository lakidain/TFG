package com.auditorias.springboot.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.auditorias.springboot.backend.model.Empresa;

@Mapper
public interface EmpresaMapper {

	@Select("select * from asi_companies where type_company=1")
	List<Empresa> findAllAuditing();
	
	@Select("select * from asi_companies where type_company=2")
	List<Empresa> findAllAudited();

	@Select("select * from asi_companies where name_company=#{name_company}")
	List<Empresa> getId(String name_company);
	
	@Select("select * from asi_companies where id_company=#{id}")
	List<Empresa> getCompanyName(Long id);
	
	/*
	 * Updates a Company
	 */
	@Update("update asi_companies set cif_company=#{cif_company},business_name_company=#{business_name_company},adress_company=#{adress_company},email_company=#{email_company},phone_company=#{phone_company} where id_company=#{id_company}")
	void updateEmpresa(Empresa empresa);
	
	@Insert("insert into asi_companies(name_company,cif_company,business_name_company,adress_company,email_company,phone_company,id_user_boss,type_company) values(#{name_company},'-','-','-','-','-',#{id},1)")
	void insertAuditCompany(Long id, String name_company);
	
	@Insert("insert into asi_companies(name_company,cif_company,business_name_company,adress_company,email_company,phone_company,id_user_boss,type_company) values(#{name_company},'-','-','-','-','-',#{id},1)")
	void updateBossAuditCompany(Long id, String name_company);
	
	@Update("update asi_companies set id_user_boss=#{id_user_boss} where id_company=#{id_company}")
	void updateBossAuditedCompany(Long id_user_boss, Long id_company);
	
	@Insert("insert into asi_companies(name_company,cif_company,business_name_company,adress_company,email_company,phone_company,id_user_boss,type_company) values(#{name_company},#{cif_company},#{business_name_company},#{adress_company},#{email_company},#{phone_company},0,2)")
	void insert(Empresa empresa);
}
