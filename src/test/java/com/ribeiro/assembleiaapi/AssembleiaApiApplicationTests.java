package com.ribeiro.assembleiaapi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
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
		HttpEntity<AgendaAddDTO> request = createRequest();

		// When
		ResponseEntity<ResponseDTO> response = create(request);
		
		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	private HttpEntity<AgendaAddDTO> createRequest() {
		return new HttpEntity<AgendaAddDTO>(createPayload());
	}

	private ResponseEntity<ResponseDTO> create(final HttpEntity<AgendaAddDTO> request) {
		return restTemplate.postForEntity(getUrl(), request, ResponseDTO.class);
	}
	
	private AgendaAddDTO createPayload() {
		return new AgendaAddDTO("my description");
	}

	private String getUrl() {
		return "http://localhost:" + this.port + "/agenda";
	}

}
