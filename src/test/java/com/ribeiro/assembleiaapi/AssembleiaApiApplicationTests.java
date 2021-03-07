package com.ribeiro.assembleiaapi;

import java.util.Calendar;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
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
import com.ribeiro.assembleiaapi.model.dto.AgendaExpirationDTO;
import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.entity.Member;
import com.ribeiro.assembleiaapi.model.enums.VoteEnum;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;

@SpringBootTest(classes = AssembleiaApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AssembleiaApiApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void givenDescription_whenPostAgenda_thenStatusCreated() {
		// Given
		HttpEntity<AgendaAddDTO> request = createAddAgendaRequest();

		// When
		ResponseEntity<ResponseDTO> response = createAgenda(request);

		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void givenOpenedAgenda_whenVote_thenStatusCreated() {
		// Given
		// All this configuration can be done through repositories, however, I chose
		// this way to exercise other endpoints
		HttpEntity<AgendaAddDTO> addAgendaRequest = createAddAgendaRequest();
		createAgenda(addAgendaRequest);
		createMember(createMemberRequest());
		openSession(createOpenSessionRequest());
		HttpEntity<VoteDTO> voteRequest = createVoteRequest();

		// When
		ResponseEntity<ResponseDTO> response = vote(voteRequest);

		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	private HttpEntity<AgendaAddDTO> createAddAgendaRequest() {
		return new HttpEntity<>(new AgendaAddDTO("my description"));
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

	private HttpEntity<VoteDTO> createVoteRequest() {
		// TODO agenda id
		return new HttpEntity<>(new VoteDTO(null, VoteEnum.SIM, 1L, 63940342076L));
	}

	private ResponseEntity<ResponseDTO> createAgenda(final HttpEntity<AgendaAddDTO> request) {
		return restTemplate.postForEntity(getUrl("/agenda"), request, ResponseDTO.class);
	}

	private ResponseEntity<ResponseDTO> createMember(final HttpEntity<MemberDTO> request) {
		return restTemplate.postForEntity(getUrl("/member"), request, ResponseDTO.class);
	}

	private ResponseEntity<ResponseDTO> openSession(final HttpEntity<AgendaExpirationDTO> request) {
		return restTemplate.exchange(getUrl("/agenda/1"), HttpMethod.PUT, request, ResponseDTO.class);
	}

	private ResponseEntity<ResponseDTO> vote(final HttpEntity<VoteDTO> request) {
		return restTemplate.postForEntity(getUrl("/vote"), request, ResponseDTO.class);
	}

	private String getUrl(String path) {
		return "http://localhost:" + this.port + path;
	}

}
