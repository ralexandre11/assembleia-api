package com.ribeiro.assembleiaapi.Service.impl;

import java.util.Collections;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.repository.AgendaRepository;
import com.ribeiro.assembleiaapi.service.impl.AgendaServiceImpl;

@ExtendWith(MockitoExtension.class)
class AgendaServiceImplTest {

	AgendaServiceImpl agendaService;
	
	@Mock
	AgendaRepository agendaRepository;
	
	
	@BeforeEach
	void setup() {
		agendaService = new AgendaServiceImpl(agendaRepository);
	}
	
	@Test
	void givenDto_whenSave_thenAgendaIsSaved() {
		AgendaAddDTO agendaAddDTO = new AgendaAddDTO("my description");
		Agenda agenda = new Agenda(1L, "my description ", new Date(), Collections.emptyList());
		Mockito.when(agendaRepository.save(Mockito.any())).thenReturn(agenda);
		
		AgendaDTO agendaDTO = agendaService.save(agendaAddDTO);
		
		ArgumentCaptor<Agenda> agendaCaptor = ArgumentCaptor.forClass(Agenda.class);
		Mockito.verify(agendaRepository).save(agendaCaptor.capture());
		Assertions.assertThat(agendaCaptor.getValue().getDescription()).isEqualTo("my description");
		
		Assertions.assertThat(agendaDTO.getId()).isEqualTo(agenda.getId());
		Assertions.assertThat(agendaDTO.getDescription()).isEqualTo(agenda.getDescription());
		Assertions.assertThat(agendaDTO.getExpiration()).isEqualTo(agenda.getExpiration());
		Assertions.assertThat(agendaDTO.getVotes()).isEqualTo(agenda.getVotes());
	}

}
