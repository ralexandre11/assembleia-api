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

import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;
import com.ribeiro.assembleiaapi.service.VoteService;

@ExtendWith(MockitoExtension.class)
class VoteControllerTest {
	
	VoteController voteController;

	@Mock
	VoteService voteService;

	@BeforeEach
	void setup() {
		voteController = new VoteController(voteService);
	}

	@Test
	void givenVote_whenCreateVote_thenVoteIsCreated() {
		// Given
		VoteDTO voteDto = Mockito.mock(VoteDTO.class);
		
		Mockito.doNothing().when(voteService).registerVote(voteDto);

		// When
		ResponseEntity<ResponseDTO> response = voteController.createVote(voteDto);

		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		ResponseDTO responseDTO = response.getBody();
		Assertions.assertThat(responseDTO.getMessage()).isEqualTo("Vote successfully registered!");
	}


}
