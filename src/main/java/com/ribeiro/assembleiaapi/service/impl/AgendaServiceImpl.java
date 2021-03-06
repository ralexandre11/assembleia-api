package com.ribeiro.assembleiaapi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.mappers.AgendaMapper;
import com.ribeiro.assembleiaapi.model.repository.AgendaRepository;
import com.ribeiro.assembleiaapi.service.AgendaService;

import lombok.AllArgsConstructor;

/**
 * Class that implements the Agenda service layer
 * @author Ricardo Ribeiro (https://www.linkedin.com/in/ricardoalexandreribeiro/)
 * @since 01/03/2021
 *
 */
@Service
@AllArgsConstructor
public class AgendaServiceImpl implements AgendaService {

	private final AgendaRepository agendaRepository;

	/**
	 * Method to create new agenda
	 * @param dto
	 * @return AgendaDTO
	 */
	@Override
	public AgendaDTO save(AgendaAddDTO dto) {
		try {
			//TODO change mapper to be not static and change test AgendaServiceImplTest.givenDto_whenSave_thenAgendaIsSaved()
			Agenda agenda = AgendaMapper.fromDTO(dto);
			
			Agenda agendaSaved = agendaRepository.save(agenda);
			
			//TODO change mapper to be not static and change test AgendaServiceImplTest.givenDto_whenSave_thenAgendaIsSaved()
			return AgendaMapper.toDTO(agendaSaved);
		} catch (ApiException a) {
			//TODO review: only throw, could be removed
			throw a;
		} catch (Exception e) {
			//TOD review: service should not know HttpStatus!!!
			throw new ApiException("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Method to Open new agenda session
	 * @param id
	 * @param dto
	 * @return AgendaDTO
	 */
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
	
	/**
	 * Method to return all agendas
	 * @return List<AgendaDTO>
	 */
	@Override
	public List<AgendaDTO> getAll() {
		try {
			List<Agenda> agendas =  agendaRepository.findAll();
			return AgendaMapper.toDtoList(agendas); 
		} catch (Exception e) {
			return new ArrayList<>();		}
	}

	/**
	 * Method to get agenda by ID
	 * @param id
	 * @return Agenda
	 */
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

	/**
	 * Method to return a date plus one minute
	 * @return Date
	 */
	private Date oneMinuteExpiration() {
		Date date = new Date();
		long curTimeInMs = date.getTime();
		return new Date(curTimeInMs + 60000);
	}

}
