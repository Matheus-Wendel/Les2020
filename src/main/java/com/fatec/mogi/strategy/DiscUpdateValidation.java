package com.fatec.mogi.strategy;

import java.util.Optional;

import com.fatec.mogi.model.domain.Disc;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.DiscRepository;

public class DiscUpdateValidation implements IStrategy {

	private DiscRepository discRepository;

	public DiscUpdateValidation(DiscRepository discRepository) {
		this.discRepository = discRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var disc = (Disc) entity;
		var sb = new StringBuilder();

		Optional<Disc> findById = discRepository.findById(disc.getId());
		if(findById.isEmpty()) {
			sb.append("Disco Invalido;;");
			return sb.toString();
		}
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
		Disc dbDisc = findById.get();

		if (disc.getActive()) {

			if(dbDisc.getTotalStock()==0) {
				sb.append("Disco sem estoque, impossivel ativar;;");
			}
			
		}
		if(disc.getActivationDetails()==null||disc.getActivationDetails().getId()==null) {
			sb.append("Necessario preencher detalhes de ativação");
			return sb.toString();
		}
		if(disc.getActivationDetails().getCategory()==null||disc.getActivationDetails().getCategory().isBlank()) {
			sb.append("Categoria de ativação / inativação é obrigatório");
		}
		if(disc.getActivationDetails().getMotive()==null||disc.getActivationDetails().getMotive().isBlank()) {
			sb.append("Categoria de ativação / inativação é obrigatório");
		}


		return sb.toString();
	}

}
