package com.ribeiro.assembleiaapi.service;

import com.ribeiro.assembleiaapi.model.entity.Agenda;

public interface AgendaKafkaService {

	public void send(Agenda agenda);
	
}
