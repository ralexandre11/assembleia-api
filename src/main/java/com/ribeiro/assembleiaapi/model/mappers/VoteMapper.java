package com.ribeiro.assembleiaapi.model.mappers;

import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.entity.Vote;

public class VoteMapper {
	
	public static Vote fromDTO(VoteDTO vote) {
		return new Vote();
	}
	
	public static VoteDTO toDTO(Vote vote) {
		return new VoteDTO();
	}

}
