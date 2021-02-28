package com.ribeiro.assembleiaapi.Service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ribeiro.assembleiaapi.Service.AgendaService;
import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.mappers.AgendaMapper;
import com.ribeiro.assembleiaapi.model.repository.AgendaRepository;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	AgendaRepository agendaRepository;

	@Override
	public AgendaDTO save(AgendaDTO dto) {
		try {
			Agenda agenda = AgendaMapper.fromDTO(dto);
			
			Agenda agendaSaved = agendaRepository.save(agenda);
			return AgendaMapper.toDTO(agendaSaved);
		} catch (ApiException a) {
			throw a;
		} catch (Exception e) {
			throw new ApiException("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public AgendaDTO update(Long id, AgendaDTO dto) {
		try {
			Agenda agenda = this.getById(id);
			if(dto.getExpiration() == null)	{
				agenda.setExpiration(oneMinuteExpiration());
			} else {
				agenda.setExpiration(dto.getExpiration());
			}
			Agenda agendaSaved = agendaRepository.save(agenda);
			return AgendaMapper.toDTO(agendaSaved);
		} catch (ApiException a) {
			throw a;
		} catch (Exception e) {
			throw new ApiException("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public List<AgendaDTO> getAll() {
		try {
			List<Agenda> agendas =  agendaRepository.findAll();
			return AgendaMapper.toDtoList(agendas); 
		} catch (Exception e) {
			return new ArrayList<>();		}
	}

	@Override
	public Agenda getById(Long id) {
		try {
			Optional<Agenda> agenda = agendaRepository.findById(id);
			if (agenda.isPresent()) {
				return agenda.get();
			}
			throw new ApiException("Agenda Not Found.", HttpStatus.NOT_FOUND);
		} catch (ApiException a) {
			throw a;
		} catch (Exception e) {
			throw new ApiException("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Date oneMinuteExpiration() {
		Date date = new Date();
		long curTimeInMs = date.getTime();
		return new Date(curTimeInMs + 60000);
	}

}
