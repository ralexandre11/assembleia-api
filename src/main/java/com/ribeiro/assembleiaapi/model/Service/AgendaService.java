package com.ribeiro.assembleiaapi.model.Service;

import java.util.List;
import java.util.Optional;

import com.ribeiro.assembleiaapi.model.entity.Agenda;

public interface AgendaService {

	Agenda save(Agenda agenda);
	
	Agenda update(Agenda agenda);
	
	void delete(Agenda agenda);
	
	List<Agenda> all();
	
	Optional<Agenda> getByID(Long id);
	
}
