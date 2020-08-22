package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fatec.mogi.enumeration.SaleStatusEnum;
@Entity
public class Sale extends DomainEntity{
	@Column(nullable = false)
	private double profit;
	@Column(nullable = false)
	private SaleStatusEnum status;

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public SaleStatusEnum getStatus() {
		return status;
	}

	public void setStatus(SaleStatusEnum status) {
		this.status = status;
	}

}
