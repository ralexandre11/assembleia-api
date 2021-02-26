package com.ribeiro.assembleiaapi.model.dto;

import com.ribeiro.assembleiaapi.model.enums.VoteValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteDTO {

	private Long id;
	
	private VoteValue voteValue;
	
	private Long idAgenda;

	private MemberDTO member;

}
