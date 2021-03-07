package com.ribeiro.assembleiaapi.service;

import java.util.List;

import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;

public interface AgendaService {

	AgendaDTO save(AgendaAddDTO dto);

	AgendaDTO update(Long id, AgendaDTO dto);

	List<AgendaDTO> getAll();

	// TODO: service does not have a consistent interface, sometimes returns
	// AgendaDTO, sometimes returns Agenda
	Agenda getById(Long id);

}
