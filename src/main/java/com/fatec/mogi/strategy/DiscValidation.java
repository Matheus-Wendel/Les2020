package com.fatec.mogi.strategy;

import com.fatec.mogi.model.domain.ActivationDetails;
import com.fatec.mogi.model.domain.Disc;
import com.fatec.mogi.model.domain.DomainEntity;

public class DiscValidation implements IStrategy {

	public DiscValidation() {

	}

	@Override
	public String process(DomainEntity entity) {
		var disc = (Disc) entity;
		var sb = new StringBuilder();

		if (disc.getArtists() == null || disc.getArtists().isEmpty()) {
			sb.append("Selecione ao menos um artista para o disco;;");
		}
		if (disc.getGenres() == null || disc.getArtists().isEmpty()) {
			sb.append("Selecione ao menos um gênero musical para o disco;;");
		}
		if (disc.getImgLink() == null || disc.getImgLink().isBlank()) {
			sb.append("Capa do album não pode ficar vazio;;");
		}
		if (disc.getDescription() == null || disc.getDescription().isBlank()) {
			sb.append("Descrição não pode ficar vazio;;");
		}
		if (disc.getName() == null || disc.getName().isBlank()) {
			sb.append("Nome não pode ficar vazio;;");
		}
		if (disc.getReleaseDate() == null) {
			sb.append("Data de lançamento não pode ficar vazio;;");
		}
		if (disc.getRecorder() == null || disc.getRecorder().getId() == null) {
			sb.append("Gravadora não pode ficar vazio;;");
		}
		if (disc.getPricing() == null || disc.getPricing().getId() == null) {
			sb.append("Precificação não pode ficar vazio;;");
		}
		disc.setActive(false);
		ActivationDetails activationDetails = new ActivationDetails();
		activationDetails.setCategory("FORA DE MERCADO");
		activationDetails.setMotive("INATIVAÇÂO AUTOMÁTICA");
		
		disc.setActivationDetails(activationDetails);


		return sb.toString();
	}

}
