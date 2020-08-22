package com.fatec.mogi.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fatec.mogi.util.ConstantsUtil;

@Entity
public class Disc extends DomainEntity {
	@Column(nullable = false)
	private boolean active;
	@Column(nullable = false)
	private double value;
	@Column(nullable = false, unique = true)
	private String code;
	@Column(nullable = false)
	private String imgLink;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	@DateTimeFormat(pattern = ConstantsUtil.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	@Column(nullable = false)
	private String name;
	// TODO CHECK IF THIS ATRIBUTE IS REALLY NEEDED OR CAN BE CHANGED TO AN ENUM
	@Column(nullable = false)
	private String status;
	@OneToOne
	private ActivationDetails activationDetails;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ActivationDetails getActivationDetails() {
		return activationDetails;
	}

	public void setActivationDetails(ActivationDetails activationDetails) {
		this.activationDetails = activationDetails;
	}

}
