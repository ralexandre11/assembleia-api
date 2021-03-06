package com.ribeiro.assembleiaapi.service;

import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteResultDTO;

public interface VoteService {

	void registerVote(VoteDTO voteDTO);
	
	VoteResultDTO getResultVotes(Long idAgenda);
	
//	List<Vote> all();
//	
//	Optional<Vote> getByID(Long id);
	
}
