package com.ribeiro.assembleiaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.service.AgendaKafkaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendaKafkaServiceImpl implements AgendaKafkaService {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public void send(Agenda agenda) {
		String agendaJson;
		try {
			agendaJson = new ObjectMapper().writeValueAsString(agenda);
			kafkaTemplate.send("finished-voting",agendaJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
