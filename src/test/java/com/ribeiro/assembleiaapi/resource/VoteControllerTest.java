package com.ribeiro.assembleiaapi.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

//	@PostMapping
//	@Operation(summary = "Register a new vote")
//	public ResponseEntity<ResponseDTO> createVote(@RequestBody VoteDTO dto) {

//	@Test
//	void givenVote_whenCreateVote_thenVoteIsCreated() {
//		// Given
//		VoteDTO voteDto = Mockito.mock(VoteDTO.class);
//		VoteDTO savedVoteDto = VoteDTO.builder().idAgenda(1L).cpf(87726955015L).voteValue(VoteEnum.SIM).build();
//		
//		Mockito.when(voteService.registerVote(voteDto)).thenReturn(savedVoteDto);
//
//		// When
//		ResponseEntity<ResponseDTO> response = voteController.createVote(voteDto);
//
//		// Then
//		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//		ResponseDTO responseDTO = response.getBody();
//		Assertions.assertThat(responseDTO.getMessage()).isEqualTo("Vote successfully registered!");
//	}


}
