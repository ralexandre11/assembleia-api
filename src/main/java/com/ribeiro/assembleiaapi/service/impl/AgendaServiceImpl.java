package com.ribeiro.assembleiaapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.mappers.AgendaMapper;
import com.ribeiro.assembleiaapi.model.repository.AgendaRepository;
import com.ribeiro.assembleiaapi.service.AgendaKafkaService;
import com.ribeiro.assembleiaapi.service.AgendaService;

import lombok.AllArgsConstructor;

/**
 * Class that implements the Agenda service layer
 * 
 * @author Ricardo Ribeiro
 *         (https://www.linkedin.com/in/ricardoalexandreribeiro/)
 * @since 01/03/2021
 *
 */
@Service
@AllArgsConstructor
public class AgendaServiceImpl implements AgendaService {

	private final AgendaRepository agendaRepository;
	
	private final AgendaMapper agendaMapper;

	private final AgendaKafkaService agendaKafkaService;

	/**
	 * Method to create new agenda
	 * 
	 * @param dto
	 * @return AgendaDTO
	 */
	@Override
	@Transactional
	public AgendaDTO save(AgendaAddDTO dto) {
		validateDescription(dto.getDescription());
		Agenda agenda = agendaMapper.fromDTO(dto);
		
		Agenda agendaSaved = agendaRepository.save(agenda);
		
		return agendaMapper.toDTO(agendaSaved);
	}
	
	@Override
	@Transactional
	public void updateFinishedAgenda(Agenda agenda) {
		agenda.setFinished(true);
		agendaRepository.save(agenda);
		
		agendaKafkaService.send(agenda);
	}

	/**
	 * Method to Open new agenda session
	 * 
	 * @param id
	 * @param dto
	 * @return AgendaDTO
	 */
	@Override
	@Transactional
	public AgendaDTO update(Long id, AgendaDTO dto) {
		Agenda agenda = this.getById(id);
		
		if(dto.getExpiration() == null)	{
			agenda.setExpiration(oneMinuteExpiration());
		} else {
			agenda.setExpiration(dto.getExpiration());
		}
		Agenda agendaSaved = agendaRepository.save(agenda);
		return agendaMapper.toDTO(agendaSaved);
	}

	/**
	 * Method to return all agendas
	 * 
	 * @return List<AgendaDTO>
	 */
	@Override
	public List<AgendaDTO> getAll() {
		List<Agenda> agendas =  agendaRepository.findAll();

		return agendaMapper.toDtoList(agendas); 
	}

	/**
	 * Method to get agenda by ID
	 * 
	 * @param id
	 * @return Agenda
	 */
	@Override
	public Agenda getById(Long id) {
		Optional<Agenda> agenda = agendaRepository.findById(id);
		if (agenda.isPresent()) {
			return agenda.get();
		} else {
			throw new ApiException("Agenda Not Found");
		}
	}

	private void validateDescription(String description) {
		if(description.length() > 50) {
			throw new ApiException("Invalid Description!");
		}
	}
	
	/**
	 * Method to return a date plus one minute
	 * 
	 * @return Date
	 */
	private Date oneMinuteExpiration() {
		Date date = new Date();
		long curTimeInMs = date.getTime();
		return new Date(curTimeInMs + 60000);
	}

}
