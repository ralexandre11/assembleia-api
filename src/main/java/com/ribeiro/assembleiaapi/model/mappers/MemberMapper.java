package com.ribeiro.assembleiaapi.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.model.entity.Member;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberMapper {

	public Member fromDTO(MemberDTO memberDTO) {
		Member member = new Member();
		member.setName(memberDTO.getName());
		member.setCpf(memberDTO.getCpf());
		return member;
	}

	public MemberDTO toDTO(Member member) {
		MemberDTO dto = new MemberDTO();
		dto.setId(member.getId());
		dto.setName(member.getName());
		dto.setCpf(member.getCpf());
		return dto;
	}

	public List<MemberDTO> toDtoList(List<Member> members) {
		List<MemberDTO> memberDtos = new ArrayList<>();
		for (Member member : members) {
			MemberDTO dto = new MemberDTO();
			BeanUtils.copyProperties(member, dto);
			memberDtos.add(dto);
		}
		return memberDtos;
	}

}
