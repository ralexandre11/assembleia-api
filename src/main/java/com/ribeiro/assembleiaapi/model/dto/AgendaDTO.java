package com.ribeiro.assembleiaapi.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgendaDTO {
	
	private Long id;
	
	private String description;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT-3")
	private Date expiration;
	
	private List<VoteDTO> votes = new ArrayList<>();

}
