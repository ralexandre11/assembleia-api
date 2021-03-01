package com.ribeiro.assembleiaapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VoteResultDTO {

	private Long idAgenda;
	
	private Long sim;
	
	private Long nao;

}
