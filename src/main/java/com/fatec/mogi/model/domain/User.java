package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fatec.mogi.enumeration.PermissionEnum;

@Entity
public class User extends DomainEntity {

	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PermissionEnum permission;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PermissionEnum getPermission() {
		return permission;
	}

	public void setPermission(PermissionEnum permission) {
		this.permission = permission;
	}

}
