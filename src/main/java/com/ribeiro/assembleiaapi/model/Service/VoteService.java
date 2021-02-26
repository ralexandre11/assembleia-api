package com.ribeiro.assembleiaapi.model.Service;

import java.util.List;
import java.util.Optional;

import com.ribeiro.assembleiaapi.model.entity.Vote;

public interface VoteService {

	Vote save(Vote vote);
	
	Vote update(Vote vote);
	
	void delete(Vote vote);
	
	List<Vote> all();
	
	Optional<Vote> getByID(Long id);
	
}
