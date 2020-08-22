package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Pricing extends DomainEntity {
	@Column(nullable = false)
	private double defautProfit;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private double minimumProfit;
	@OneToOne
	private Sale sale;

	public double getDefautProfit() {
		return defautProfit;
	}

	public void setDefautProfit(double defautProfit) {
		this.defautProfit = defautProfit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMinimumProfit() {
		return minimumProfit;
	}

	public void setMinimumProfit(double minimumProfit) {
		this.minimumProfit = minimumProfit;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

}
