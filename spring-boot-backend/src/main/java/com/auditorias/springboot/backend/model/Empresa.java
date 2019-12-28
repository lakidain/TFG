package com.auditorias.springboot.backend.model;

public class Empresa {

	private int id_company;
	private String name_company;
	private Long id_user_boss;

	public int getId_company() {
		return id_company;
	}

	public void setId_company(int id_company) {
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
