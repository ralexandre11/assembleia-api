package com.ribeiro.assembleiaapi.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteResultDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;
import com.ribeiro.assembleiaapi.service.VoteService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

/**
 * Class responsible for making the endpoint available: "/vote"
 * @author Ricardo Ribeiro (https://www.linkedin.com/in/ricardoalexandreribeiro/)
 * @since 01/03/2021
 *
 */
@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

	private final VoteService service;

	/**
	 * Method to return the voting result
	 * @param idAgenda
	 * @return VoteResultDTO
	 */
	@GetMapping("/{idAgenda}")
	@Operation(summary = "Return the voting result")
	public ResponseEntity<VoteResultDTO> votingResult(@PathVariable("idAgenda") Long idAgenda) {

		VoteResultDTO votingResult = service.getResultVotes(idAgenda);
		
		return ResponseEntity.status(HttpStatus.OK).body(votingResult);
	}
	
	/**
	 * Method for registering a new vote
	 * @param dto
	 * @return ResponseDTO
	 */
	@PostMapping
	@Operation(summary = "Register a new vote")
	public ResponseEntity<ResponseDTO> createVote(@RequestBody VoteDTO dto) {
		
		service.registerVote(dto);
		
		ResponseDTO response = new ResponseDTO("Vote OK!");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
