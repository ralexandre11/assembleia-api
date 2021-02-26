package com.ribeiro.assembleiaapi.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgendaDTO {
	
	private Long id;
	
	private String description;
	
	private Date expiration;
	
	private List<VoteDTO> votes = new ArrayList<>();

}
