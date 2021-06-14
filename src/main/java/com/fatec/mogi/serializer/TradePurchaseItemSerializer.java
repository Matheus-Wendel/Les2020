package com.fatec.mogi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fatec.mogi.model.domain.DomainEntity;

public class TradePurchaseItemSerializer extends JsonSerializer<DomainEntity> {

	@Override
	public void serialize(DomainEntity value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeEndObject();
		
	}

}
