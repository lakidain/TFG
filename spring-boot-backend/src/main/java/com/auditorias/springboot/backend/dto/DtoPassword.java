package com.auditorias.springboot.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity DtoPassword
 */
public class DtoPassword {

	@NotNull
	@Size(min = 6, max = 15)
	private String oldPassword;

	@NotNull
	@Size(min = 6, max = 15)
	private String newPassword;

	/**
	 * Get parameter oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * Set parameter oldPassword
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * Get parameter newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Set parameter newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
