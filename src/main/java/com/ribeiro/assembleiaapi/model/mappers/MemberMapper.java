package com.ribeiro.assembleiaapi.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.model.entity.Member;

public class MemberMapper {

	public static Member fromDTO(MemberDTO member) {
		return new Member();
	}
	
	public static MemberDTO toDTO(Member member) {
		return new MemberDTO();
	}
	
	 public static List<MemberDTO> toDtoList(List<Member> members) {
	        List<MemberDTO> memberDtos = new ArrayList<>();
	        for (Member member : members) {
	        	MemberDTO dto = new MemberDTO();
	            BeanUtils.copyProperties(member, dto);
	            memberDtos.add(dto);
	        }
	        return memberDtos;
	    }
	
}
