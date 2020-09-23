package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.Recorder;
@Service
public class RecorderDAO extends AbstractDAO<Recorder> {

	@Autowired
	public RecorderDAO(JpaRepository<Recorder, Integer> repository) {
		super(repository);
	}

	
}
