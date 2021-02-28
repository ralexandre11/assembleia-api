package com.ribeiro.assembleiaapi.Service;

import java.util.List;

import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;

public interface AgendaService {

	AgendaDTO save(AgendaDTO dto);
	
	AgendaDTO update(Long id, AgendaDTO dto);
	
	void delete(Agenda agenda);
	
	List<AgendaDTO> getAll();
	
	Agenda getById(Long id);
	
}
