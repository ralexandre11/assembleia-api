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

import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;
import com.ribeiro.assembleiaapi.service.MemberService;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {
	
	MemberController memberController;

	@Mock
	MemberService memberService;

	@BeforeEach
	void setup() {
		memberController = new MemberController(memberService);
	}

//	public ResponseEntity<ResponseDTO> createMember(@RequestBody MemberDTO dto) {
//		MemberDTO dtoSaved = service.save(dto);
//		ResponseDTO response = new ResponseDTO("Created Member! ID: " + dtoSaved.getId());
//		return ResponseEntity.status(HttpStatus.CREATED).body(response);
//	}	

	@Test
	void givenMember_whenCreateMember_thenMemberIsCreated() {
		// Given
		MemberDTO memberDto = MemberDTO.builder().name("User Test").cpf(87726955015L).build();
		MemberDTO savedMemberDto = MemberDTO.builder().id(1L).name("User Test").cpf(87726955015L).build();
		
		Mockito.when(memberService.save(memberDto)).thenReturn(savedMemberDto);

		// When
		ResponseEntity<ResponseDTO> response = memberController.createMember(memberDto);

		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		ResponseDTO responseDTO = response.getBody();
		Assertions.assertThat(responseDTO.getMessage()).isEqualTo("Created Member! ID: 1");
	}

	

}
