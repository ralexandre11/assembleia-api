package com.ribeiro.assembleiaapi.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.mappers.AgendaMapper;
import com.ribeiro.assembleiaapi.service.AgendaKafkaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendaKafkaServiceImpl implements AgendaKafkaService {

	private final AgendaMapper agendaMapper;
	
	private final KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public void send(Agenda agenda) {
		String agendaJson;
		try {
			agenda.setVotes(new ArrayList<>());
			AgendaDTO agendaDto = agendaMapper.toDTO(agenda);
			agendaJson = new ObjectMapper().writeValueAsString(agendaDto);
			
			Logger.getLogger(this.getClass().getName()).info("Kafka json:" + agendaJson);
			
			kafkaTemplate.send("finished-voting",agendaJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
