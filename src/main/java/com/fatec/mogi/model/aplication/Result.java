package com.fatec.mogi.model.aplication;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fatec.mogi.model.domain.DomainEntity;

public class Result extends AplicationEntity {
	private Map<String, String> messages;
	private List<? extends DomainEntity> resultList;
	private Boolean error;

	@SuppressWarnings("rawtypes")
	public ResponseEntity buildResponse() {
		if (this.error) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages);
		} else if (this.resultList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}

	public List<? extends DomainEntity> getResultList() {
		return resultList;
	}

	public void setResultList(List<? extends DomainEntity> resultList) {
		this.resultList = resultList;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

}
