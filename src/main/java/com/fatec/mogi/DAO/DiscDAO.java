package com.fatec.mogi.DAO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.Artist;
import com.fatec.mogi.model.domain.Disc;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Genre;
import com.fatec.mogi.model.domain.Recorder;
import com.fatec.mogi.repository.ActivationDetailsRepository;
import com.fatec.mogi.repository.DiscRepository;
import com.fatec.mogi.repository.StockRepository;

@Service
public class DiscDAO extends AbstractDAO<Disc> {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	ActivationDetailsRepository activationDetailsRepository;

	DiscRepository discRepository;

	@Autowired
	public DiscDAO(JpaRepository<Disc, Integer> repository) {
		super(repository);
		this.discRepository = (DiscRepository) repository;
	}

	@Override
	@Transactional
	public Result save(Filter<? extends DomainEntity> filter) {
		var disc = (Disc) filter.getEntity();
		activationDetailsRepository.save(disc.getActivationDetails());
		return super.save(filter);
	}

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		// var disc = (Disc) filter.getEntity();
		Map<String, String> allparameters = filter.getParameters();
		Map<String, String> parameters = new HashMap<String, String>();

		allparameters.forEach((key, value) -> {
			if (!value.isBlank()) {
				parameters.put(key, value);
			}
		});
		Disc disc = new Disc();
		Result result = new Result();
		try {

			var loggedUser = AuthUtils.getLoggedUser();
			if (loggedUser == null || loggedUser.getPermission() == PermissionEnum.CLIENT) {
				disc.setActive(true);
			} else {
				if (parameters.containsKey("active")) {
					disc.setActive(Boolean.valueOf(parameters.get("active")));
				}
			}

			disc.setDescription(parameters.get("description"));
			disc.setName(parameters.get("name"));
			if (parameters.containsKey("recorder")) {
				Recorder recorder = new Recorder();
				recorder.setId(Integer.parseInt(parameters.get("recorder")));
				disc.setRecorder(recorder);
			}
			if (parameters.containsKey("artist")) {
				Artist artist = new Artist();
				artist.setId(Integer.parseInt(parameters.get("artist")));
				disc.setArtists(Arrays.asList(artist));
			}
			if (parameters.containsKey("genre")) {
				Genre genre = new Genre();
				genre.setId(Integer.parseInt(parameters.get("genre")));
				disc.setGenres(Arrays.asList(genre));
			}
			ExampleMatcher matcher = ExampleMatcher.matching()
					.withMatcher("name",new GenericPropertyMatcher().contains()).withIgnoreCase()
					.withMatcher("description",new GenericPropertyMatcher().contains()).withIgnoreCase();
			
			
			Example<Disc> example = Example.of(disc, matcher);
			
			result.setResultList(discRepository.findAll(example));
			return result;
		} catch (Exception e) {
			result.setError(true);
			result.getMessages().put("Mensagem", e.getMessage());
			result.getMessages().put("Causa", e.getCause().toString());
			return result;
		}
	}

}
