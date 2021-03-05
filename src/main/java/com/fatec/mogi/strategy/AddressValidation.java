package com.fatec.mogi.strategy;

import com.fatec.mogi.model.domain.Address;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.CityRepository;

public class AddressValidation implements IStrategy {

	CityRepository cityRepository;

	public AddressValidation(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var address = (Address) entity;
		var sb = new StringBuilder();

		if (address.getAddressDescription() == null || address.getAddressDescription().isBlank()) {
			sb.append("Lougradoro não pode ser vazio ex: Rua da silva...;;");
		}
		if (address.getDescription() == null || address.getDescription().isBlank()) {
			sb.append("Descição do endereço não pode ser vazia ex: casa, apartamento, trabalho...;;");
		}
		if (address.getAddressType() == null || address.getAddressType().isBlank()) {
			sb.append("Tipo do lougradouro não pode ser Vazio ex: Rua, via, praça;;");
		}
		if (address.getDistrict() == null || address.getDistrict().isBlank()) {
			sb.append("Bairro não pode ficar vazio;;");
		}
		if (address.getNumber() == null || address.getNumber().isBlank()) {
			sb.append("Numero não pode ficar vazio;;");
		}
		if (address.getCity() == null || address.getCity().getId() == null) {
			sb.append("Cidade não pode ficar vazia;;");
		}
		if (sb.length() == 0) {
			var exist = cityRepository.existsById(address.getCity().getId());
			if (!exist) {
				sb.append("id de cidade Invalida;;");
			}
		}
		return sb.toString();
	}

}
