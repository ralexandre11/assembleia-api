package com.ribeiro.assembleiaapi.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.entity.Member;
import com.ribeiro.assembleiaapi.model.entity.Vote;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class VoteMapper {
	
	public Vote fromDTO(VoteDTO voteDTO, Agenda agenda, Member member) {
		Vote vote = new Vote();
		vote.setAgenda(agenda);
		vote.setMember(member);
		vote.setVoteValue(voteDTO.getVoteValue());
		return vote;
	}
	
	public List<VoteDTO> toDtoList(List<Vote> votes) {
		List<VoteDTO> voteDTOs = new ArrayList<>();
		for (Vote vote : votes) {
			VoteDTO dto = new VoteDTO();
			dto.setId(vote.getId());
			dto.setVoteValue(vote.getVoteValue());
			dto.setIdAgenda(vote.getAgenda().getId());
			dto.setCpf(vote.getMember().getCpf());
			voteDTOs.add(dto);
		}
		return voteDTOs;
	}

}
