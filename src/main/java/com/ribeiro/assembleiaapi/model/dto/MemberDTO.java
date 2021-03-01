package com.ribeiro.assembleiaapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDTO {

	private Long id;

	private String name;

	private Long cpf;

}
