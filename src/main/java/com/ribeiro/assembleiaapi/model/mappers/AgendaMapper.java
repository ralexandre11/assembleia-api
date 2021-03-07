package com.ribeiro.assembleiaapi.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AgendaMapper {

	private final VoteMapper voteMapper;
	
	public Agenda fromDTO(AgendaAddDTO dto) {
		Agenda agenda = new Agenda();
		agenda.setDescription(dto.getDescription());
		//TODO 
		//agenda.setExpiration(agendaDTO.getExpiration());
		return agenda;
	}

	public AgendaDTO toDTO(Agenda agenda) {
		AgendaDTO dto = new AgendaDTO();
		List<VoteDTO> votes = voteMapper.toDtoList(agenda.getVotes());
		dto.setId(agenda.getId());
		dto.setDescription(agenda.getDescription());
		dto.setExpiration(agenda.getExpiration());
		dto.setVotes(votes);
		return dto;
	}

	public List<AgendaDTO> toDtoList(List<Agenda> agendas) {
		List<AgendaDTO> agendaDtos = new ArrayList<>();
		for (Agenda agenda : agendas) {
			AgendaDTO dto = new AgendaDTO();
			List<VoteDTO> voteDtos = voteMapper.toDtoList(agenda.getVotes());
			dto.setId(agenda.getId());
			dto.setDescription(agenda.getDescription());
			dto.setExpiration(agenda.getExpiration());
			dto.setVotes(voteDtos);
			agendaDtos.add(dto);
		}
		return agendaDtos;
	}

}
