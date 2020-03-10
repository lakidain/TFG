package com.auditorias.springboot.backend.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Empresa {

	private Long id_company;
	
	@NotNull
	@Size(min=1, max = 100)
	private String name_company;
	
	@NotNull
	@Size(min=1, max = 9)
	private String cif_company;
	
	@NotNull
	@Size(min=1, max = 100)
	private String business_name_company;
	
	@NotNull
	@Size(min=1, max = 100)
	private String adress_company;
	
	@NotNull
	@Size(min=1, max = 100)
	private String email_company;
	
	@NotNull
	@Size(min=1, max = 12)
	private String phone_company;
	
	public String getCif_company() {
		return cif_company;
	}

	public void setCif_company(String cif_company) {
		this.cif_company = cif_company;
	}

	public String getBusiness_name_company() {
		return business_name_company;
	}

	public void setBusiness_name_company(String business_name_company) {
		this.business_name_company = business_name_company;
	}

	public String getAdress_company() {
		return adress_company;
	}

	public void setAdress_company(String adress_company) {
		this.adress_company = adress_company;
	}

	public String getEmail_company() {
		return email_company;
	}

	public void setEmail_company(String email_company) {
		this.email_company = email_company;
	}

	public String getPhone_company() {
		return phone_company;
	}

	public void setPhone_company(String phone_company) {
		this.phone_company = phone_company;
	}

	private Long id_user_boss;

	public Long getId_company() {
		return id_company;
	}

	public void setId_company(Long id_company) {
		this.id_company = id_company;
	}

	public String getName_company() {
		return name_company;
	}

	public void setName_company(String name_company) {
		this.name_company = name_company;
	}

	public Long getId_user_boss() {
		return id_user_boss;
	}

	public void setId_user_boss(Long id_user_boss) {
		this.id_user_boss = id_user_boss;
	}

}
