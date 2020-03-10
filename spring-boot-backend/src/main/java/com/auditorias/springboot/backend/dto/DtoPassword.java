package com.auditorias.springboot.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DtoPassword {

	@NotNull
	@Size(min = 6, max = 15)
	private String oldPassword;
	
	@NotNull
	@Size(min = 6, max = 15)
	private String newPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
