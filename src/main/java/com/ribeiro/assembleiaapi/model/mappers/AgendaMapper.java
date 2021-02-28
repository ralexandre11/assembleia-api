package com.ribeiro.assembleiaapi.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;

public class AgendaMapper {

	public static Agenda fromDTO(AgendaDTO agendaDTO) {
		Agenda agenda = new Agenda();
		agenda.setDescription(agendaDTO.getDescription());
		agenda.setExpiration(agendaDTO.getExpiration());
		return agenda;
	}
	
	public static AgendaDTO toDTO(Agenda agenda) {
		AgendaDTO dto = new AgendaDTO();
		dto.setId(agenda.getId());
		dto.setDescription(agenda.getDescription());
		dto.setExpiration(agenda.getExpiration());
		return dto;
	}
	
	 public static List<AgendaDTO> toDtoList(List<Agenda> agendas) {
	        List<AgendaDTO> agendaDtos = new ArrayList<>();
	        for (Agenda agenda : agendas) {
	        	AgendaDTO dto = new AgendaDTO();
	            BeanUtils.copyProperties(agenda, dto);
	            agendaDtos.add(dto);
	        }
	        return agendaDtos;
	    }
	
}


