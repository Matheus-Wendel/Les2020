package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatec.mogi.enumeration.PermissionEnum;

@Entity
@Where(clause = "active=1")
public class User extends DomainEntity {

	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PermissionEnum permission;
	@Column(name = "active")
	private boolean active = true;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
