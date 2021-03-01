package com.ribeiro.assembleiaapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.assembleiaapi.Service.VoteService;
import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteResultDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;

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

	@Autowired
	VoteService service;

	/**
	 * Method to return a result votes 
	 * @param idAgenda
	 * @return VoteResultDTO
	 */
	@GetMapping("/{idAgenda}")
	public ResponseEntity<VoteResultDTO> resultVotes(@PathVariable("idAgenda") Long idAgenda) {
		VoteResultDTO votesResult = service.getResultVotes(idAgenda);
		return ResponseEntity.status(HttpStatus.CREATED).body(votesResult);
	}
	
	/**
	 * Method for registering a new vote
	 * @param dto
	 * @return ResponseDTO
	 */
	@PostMapping
	public ResponseEntity<ResponseDTO> createVote(@RequestBody VoteDTO dto) {
		service.registerVote(dto);
		ResponseDTO response = new ResponseDTO("Vote OK!");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
