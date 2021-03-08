package com.ribeiro.assembleiaapi.Service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.mappers.AgendaMapper;
import com.ribeiro.assembleiaapi.model.repository.AgendaRepository;
import com.ribeiro.assembleiaapi.service.AgendaKafkaService;
import com.ribeiro.assembleiaapi.service.impl.AgendaServiceImpl;

@ExtendWith(MockitoExtension.class)
class AgendaServiceImplTest {

	AgendaServiceImpl agendaService;
	
	@Mock
	AgendaMapper agendaMapper;

	@Mock
	AgendaRepository agendaRepository;

	@Mock
	AgendaKafkaService agendaKafkaService;

	@BeforeEach
	void setup() {
		agendaService = new AgendaServiceImpl(agendaRepository, agendaMapper, agendaKafkaService);
	}

	@Test
	void givenDto_whenSave_thenAgendaIsSaved() {
		// Given
		AgendaAddDTO agendaAddDto = new AgendaAddDTO("my description");
		AgendaDTO SavedAgendaDto = new AgendaDTO(1L, "my description ", new Date(), Collections.emptyList());
		Agenda agendaBeforeSave = new Agenda(null, "my description", null, false, null);
		Agenda agenda = new Agenda(1L, "my description ", new Date(), false, Collections.emptyList());
		Mockito.when(agendaRepository.save(Mockito.any())).thenReturn(agenda);
		Mockito.when(agendaMapper.fromDTO(Mockito.any())).thenReturn(agendaBeforeSave);
		Mockito.when(agendaMapper.toDTO(Mockito.any())).thenReturn(SavedAgendaDto);

		// When
		AgendaDTO agendaDTO = agendaService.save(agendaAddDto);

		// Then
		ArgumentCaptor<Agenda> agendaCaptor = ArgumentCaptor.forClass(Agenda.class);
		Mockito.verify(agendaRepository).save(agendaCaptor.capture());
		Assertions.assertThat(agendaCaptor.getValue().getDescription()).isEqualTo("my description");

		Assertions.assertThat(agendaDTO.getId()).isEqualTo(agenda.getId());
		Assertions.assertThat(agendaDTO.getDescription()).isEqualTo(agenda.getDescription());
		Assertions.assertThat(agendaDTO.getExpiration()).isEqualTo(agenda.getExpiration());
		Assertions.assertThat(agendaDTO.getVotes()).isEqualTo(agenda.getVotes());
	}

	@Test
	void givenExistingId_whenGetByID_thenAgendaReturned() {
		// Given
		Agenda agenda = new Agenda(1L, "my description", new Date(), false, Collections.emptyList());
		Mockito.when(agendaRepository.findById(1L)).thenReturn(Optional.of(agenda));

		// When
		Agenda actual = agendaService.getById(1L);

		// Then
		Assertions.assertThat(actual).isSameAs(agenda);
	}

	@Test
	void givenUnexistingId_whenGetByID_thenThrowsNotFoundException() {
		// Given
		Mockito.when(agendaRepository.findById(1L)).thenReturn(Optional.empty());

		// When
		Throwable actual = Assertions.catchThrowable(() -> agendaService.getById(1L));

		// Then
		Assertions.assertThat(actual).isInstanceOf(ApiException.class);
		ApiException apiException = (ApiException) actual;
		Assertions.assertThat(apiException.getMessage()).isEqualTo("Agenda Not Found");
	}
}
