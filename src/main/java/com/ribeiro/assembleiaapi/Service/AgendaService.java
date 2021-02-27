package com.ribeiro.assembleiaapi.Service;

import java.util.List;
import java.util.Optional;

import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;

public interface AgendaService {

	AgendaDTO save(AgendaDTO dto);
	
	Agenda update(Agenda agenda);
	
	void delete(Agenda agenda);
	
	List<Agenda> all();
	
	Optional<Agenda> getByID(Long id);
	
}
