package com.fatec.mogi.model.aplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.util.MessagesUtil;

public class Result extends AplicationEntity {
	private Map<String, String> messages = new HashMap<>();
	private List<? extends DomainEntity> resultList;
	private boolean error;

	public Result() {
	}
	
	
	public Result(Map<String, String> messages, List<? extends DomainEntity> resultList, boolean error) {
		this.messages = messages;
		this.resultList = resultList;
		this.error = error;
	}


	@SuppressWarnings("rawtypes")
	public ResponseEntity buildResponse() {
		if (this.error) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages);
		}
		if (this.messages.containsKey(MessagesUtil.NOT_FOUND)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messages);
		}
		if (this.messages.containsKey(MessagesUtil.DELETED)) {
			return ResponseEntity.status(HttpStatus.OK).body(messages);
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

	public boolean getError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

}
