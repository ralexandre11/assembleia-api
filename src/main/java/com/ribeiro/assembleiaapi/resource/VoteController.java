package com.ribeiro.assembleiaapi.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.exception.ApiExceptionController;
import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteResultDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;
import com.ribeiro.assembleiaapi.service.VoteService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

/**
 * Class responsible for making the endpoint available: "/vote"
 * 
 * @author Ricardo Ribeiro
 *         (https://www.linkedin.com/in/ricardoalexandreribeiro/)
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
	 * 
	 * @param idAgenda
	 * @return VoteResultDTO
	 */
	@GetMapping("/{idAgenda}/result")
	@Operation(summary = "Return the voting result")
	public ResponseEntity<VoteResultDTO> votingResult(@PathVariable("idAgenda") Long idAgenda) {
		VoteResultDTO votingResult = service.getResultVotes(idAgenda);

		return ResponseEntity.status(HttpStatus.OK).body(votingResult);
	}

	/**
	 * Method for registering a new vote
	 * 
	 * @param dto
	 * @return ResponseDTO
	 */
	@PostMapping()
	@Operation(summary = "Register a new vote")
	// TODO: Path could be /agendas/{idAgenda}/vote and DTO only:
	// {cpf: 1111111111, value: YES}
	// TODO: a vote can be changed?
	public ResponseEntity<ResponseDTO> createVote(@RequestBody VoteDTO dto) {
		try {
			service.registerVote(dto);

			ResponseDTO response = new ResponseDTO("Vote successfully registered!");

			// TODO: could be NO_CONTENT
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (ApiException e) {
			ResponseDTO response = new ResponseDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
