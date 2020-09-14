package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address extends DomainEntity {

	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String number;
	// TIPO DE LOUGRADOURO
	// EX RUA, PRACA, VIA ETC...
	@Column(nullable = false)
	private String addressType;
	// LOUGRADOURO
	@Column(nullable = false)
	private String addressDescription;
	// BAIRRO
	@Column(nullable = false)
	private String district;
	private String observations;
	@ManyToOne
	private City city;
	
	@ManyToOne
	@JoinColumn(name="client_delivery_id", referencedColumnName="id")
	private Client client;

	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressDescription() {
		return addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [description=" + description + ", number=" + number + ", addressType=" + addressType
				+ ", addressDescription=" + addressDescription + ", district=" + district + ", observations="
				+ observations + ", city=" + city + "]";
	}
	
	

}
