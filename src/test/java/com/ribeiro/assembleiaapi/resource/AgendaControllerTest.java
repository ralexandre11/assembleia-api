package com.ribeiro.assembleiaapi.resource;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaExpirationDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;
import com.ribeiro.assembleiaapi.service.AgendaService;

@ExtendWith(MockitoExtension.class)
class AgendaControllerTest {

	AgendaController agendaController;

	@Mock
	AgendaService agendaService;

	@BeforeEach
	void setup() {
		agendaController = new AgendaController(agendaService);
	}

	@Test
	void givenDescriptionAgenda_whenCreateAgenda_thenAgendaIsCreated() {
		// Given
		AgendaAddDTO agendaAddDto = AgendaAddDTO.builder().description("test agenda").build();

		AgendaDTO agendaDto = AgendaDTO.builder().id(1L).build();
		
		Mockito.when(agendaService.save(agendaAddDto)).thenReturn(agendaDto);

		// When
		ResponseEntity<ResponseDTO> response = agendaController.createAgenda(agendaAddDto);

		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		ResponseDTO responseDTO = response.getBody();
		Assertions.assertThat(responseDTO.getMessage()).isEqualTo("Created Agenda! ID: 1");
	}
	
	@Test
	void givenExpirationDateAgenda_whenUpdateAgenda_thenAgendaIsUpdatedWithNewExpirationDate() {
		// Given
		Date date = new Date();
		long curTimeInMs = date.getTime();
		Date dateExpiration = new Date(curTimeInMs + 60000);		
		AgendaExpirationDTO agendaExpirationDto = AgendaExpirationDTO.builder().expiration(dateExpiration).build();
		
		Long id = 1L;
		AgendaDTO agendaDto = AgendaDTO.builder().expiration(dateExpiration).build();
		AgendaDTO savedAgendaDto = AgendaDTO.builder().id(id).expiration(dateExpiration).build();
		
		Mockito.when(agendaService.update(id, agendaDto)).thenReturn(savedAgendaDto);

		// When
		ResponseEntity<ResponseDTO> response = agendaController.openSessionAgenda(id, agendaExpirationDto);

		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		ResponseDTO responseDTO = response.getBody();
		Assertions.assertThat(responseDTO.getMessage()).isEqualTo("Opened Session! ID: 1");
	}


}
