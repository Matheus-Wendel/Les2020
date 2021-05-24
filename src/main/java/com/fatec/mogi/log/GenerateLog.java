package com.fatec.mogi.log;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.util.CrudOperationEnum;

@Service
public class GenerateLog {
	@Autowired
	private LogRepository logrepository;


	public void GerarLog(DomainEntity entity, CrudOperationEnum operation) {
		Log log = new Log();

		log.setOperation(operation);
		// CADASTRO DE cliente
		// NAO EH FEITO POR NENHUM OUTRO cliente
		// SALVA COMO O PROPRIO
		if (entity.getClass() == Client.class && operation == CrudOperationEnum.SAVE) {
			Client client = (Client) entity;
			log.setUser(client.getUser());
		} else {
			log.setUser(AuthUtils.getLoggedUser());
		}
		log.setEntity(entity.getClass().getSimpleName());
		log.setEntityId(entity.getId());
		log.setOperationDate(new Date());
		logrepository.save(log);

		System.err.println(log.getUser().getEmail());
	}


}
