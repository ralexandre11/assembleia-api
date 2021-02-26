package com.ribeiro.assembleiaapi.model.mappers;

import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.model.entity.Member;

public class MemberMapper {

	public static Member fromDTO(MemberDTO member) {
		return new Member();
	}
	
	public static MemberDTO toDTO(Member member) {
		return new MemberDTO();
	}
	
}
