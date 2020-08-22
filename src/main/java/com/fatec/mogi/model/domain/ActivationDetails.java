package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class ActivationDetails extends DomainEntity {
	@Column(nullable = false)
	private String motive;
	@Column(nullable = false)
	private String category;
	
	public String getMotive() {
		return motive;
	}
	public void setMotive(String motive) {
		this.motive = motive;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
