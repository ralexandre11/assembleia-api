package com.ribeiro.assembleiaapi;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaExpirationDTO;
import com.ribeiro.assembleiaapi.model.dto.CpfApiDTO;
import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.enums.VoteEnum;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;
import com.ribeiro.assembleiaapi.service.CpfApiService;

@SpringBootTest(classes = AssembleiaApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AssembleiaApiApplicationTests {

	@LocalServerPort
	private int port;

	@Mock
	CpfApiService cpfApiService;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void givenDescription_whenPostAgenda_thenStatusCreated() {
		// Given
		HttpEntity<AgendaAddDTO> request = createAddAgendaRequest(StringUtils.repeat("D", 10));

		// When
		ResponseEntity<AgendaDTO> response = createAgenda(request);

		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void givenVeryLongDescription_whenPostAgenda_thenInternalError() {
		// Given
		HttpEntity<AgendaAddDTO> request = createAddAgendaRequest(StringUtils.repeat("D", 300));

		// When
		ResponseEntity<AgendaDTO> response = createAgenda(request);

		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void givenOpenedAgenda_whenVote_thenStatusCreated() {
		// Given
		// All this configuration can be done through repositories, however, I chose this way to exercise other endpoints
		HttpEntity<AgendaAddDTO> addAgendaRequest = createAddAgendaRequest(StringUtils.repeat("D", 10));
		ResponseEntity<AgendaDTO> agenda = createAgenda(addAgendaRequest);
		createMember(createMemberRequest());
		openSession(agenda.getBody().getId(), createOpenSessionRequest());
		HttpEntity<VoteDTO> voteRequest = createVoteRequest(agenda.getBody().getId());
		
		CpfApiDTO cpfApiDto = new CpfApiDTO("ABLE_TO_VOTE");
		Mockito.when(cpfApiService.checkCpfApi(Mockito.any())).thenReturn(cpfApiDto);
		
		// When
		ResponseEntity<ResponseDTO> response = vote(voteRequest);

		// Then
		//TODO depends on the return of the cpf validation, can fail sometimes. Need to mock cpf service
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void givenClosedAgenda_whenVote_thenStatusBadRequest() {
		// Given
		HttpEntity<AgendaAddDTO> addAgendaRequest = createAddAgendaRequest(StringUtils.repeat("D", 10));
		ResponseEntity<AgendaDTO> agenda = createAgenda(addAgendaRequest);
		HttpEntity<VoteDTO> voteRequest = createVoteRequest(agenda.getBody().getId());

		// When
		ResponseEntity<ResponseDTO> response = vote(voteRequest);

		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		Assertions.assertThat(response.getBody().getMessage()).isEqualTo("Agenda is not Opened!");
	}

	private HttpEntity<AgendaAddDTO> createAddAgendaRequest(String description) {
		return new HttpEntity<>(new AgendaAddDTO(description));
	}

	private HttpEntity<AgendaExpirationDTO> createOpenSessionRequest() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 5);
		return new HttpEntity<>(new AgendaExpirationDTO(calendar.getTime()));
	}

	private HttpEntity<MemberDTO> createMemberRequest() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 5);
		return new HttpEntity<>(new MemberDTO(null, "Test Member", 63940342076L));
	}

	private HttpEntity<VoteDTO> createVoteRequest(Long agendaId) {
		return new HttpEntity<>(new VoteDTO(null, VoteEnum.SIM, agendaId, 63940342076L));
	}

	private ResponseEntity<AgendaDTO> createAgenda(final HttpEntity<AgendaAddDTO> request) {
		return restTemplate.postForEntity(getUrl("/agenda"), request, AgendaDTO.class);
	}

	private ResponseEntity<ResponseDTO> createMember(final HttpEntity<MemberDTO> request) {
		return restTemplate.postForEntity(getUrl("/member"), request, ResponseDTO.class);
	}

	private ResponseEntity<ResponseDTO> openSession(Long agendaId, final HttpEntity<AgendaExpirationDTO> request) {
		return restTemplate.exchange(getUrl("/agenda/" + agendaId), HttpMethod.PUT, request, ResponseDTO.class);
	}

	private ResponseEntity<ResponseDTO> vote(final HttpEntity<VoteDTO> request) {
		return restTemplate.postForEntity(getUrl("/vote"), request, ResponseDTO.class);
	}

	private String getUrl(String path) {
		return "http://localhost:" + this.port + path;
	}

}
