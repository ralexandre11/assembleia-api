package com.ribeiro.assembleiaapi.resource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ribeiro.assembleiaapi.Service.AgendaService;
import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;

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
	void givenAgenda_whenCreateAgenda_thenAgendaIsCreated() {
		AgendaAddDTO agendaAddDTO = new AgendaAddDTO("test agenda");
		AgendaDTO agendaDTO = new AgendaDTO();
		agendaDTO.setId(1L);
		Mockito.when(agendaService.save(agendaAddDTO)).thenReturn(agendaDTO);

		ResponseEntity<ResponseDTO> response = agendaController.createAgenda(agendaAddDTO);

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		ResponseDTO responseDTO = response.getBody();
		Assertions.assertThat(responseDTO.getMessage()).isEqualTo("Created Agenda! ID: 1");
	}

}
