package com.ribeiro.assembleiaapi.model.dto;

import com.ribeiro.assembleiaapi.model.enums.VoteEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteDTO {

	private Long id;
	
	private VoteEnum voteValue;
	
	private Long idAgenda;

	private Long cpf;

}
