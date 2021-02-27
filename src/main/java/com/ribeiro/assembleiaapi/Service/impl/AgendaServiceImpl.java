package com.ribeiro.assembleiaapi.Service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribeiro.assembleiaapi.Service.AgendaService;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.mappers.AgendaMapper;
import com.ribeiro.assembleiaapi.model.repository.AgendaRepository;

@Service
public class AgendaServiceImpl implements AgendaService{

	@Autowired
	AgendaRepository agendaRepository;
	
	@Override
	public AgendaDTO save(AgendaDTO dto) {
		Agenda agenda = AgendaMapper.fromDTO(dto);
		if (agenda.getExpiration() == null) {
			agenda.setExpiration(oneMinuteExpiration());
		}
		
		Agenda agendaSaved = agendaRepository.save(agenda);
		return AgendaMapper.toDTO(agendaSaved);
	}

	@Override
	public Agenda update(Agenda agenda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Agenda agenda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Agenda> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Agenda> getByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	private Date oneMinuteExpiration() {
		Date date = new Date();
		long curTimeInMs = date.getTime();
		return new Date(curTimeInMs + 60000);
	}
	
	
}
