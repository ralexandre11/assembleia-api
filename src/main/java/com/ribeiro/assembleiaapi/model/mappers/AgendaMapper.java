package com.ribeiro.assembleiaapi.model.mappers;

import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;

public class AgendaMapper {

	public static Agenda fromDTO(AgendaDTO agenda) {
		return new Agenda();
	}
	
	public static AgendaDTO toDTO(Agenda agenda) {
		return new AgendaDTO();
	}
	
}
