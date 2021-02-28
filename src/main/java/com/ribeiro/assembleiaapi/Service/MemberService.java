package com.ribeiro.assembleiaapi.Service;

import java.util.List;

import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.model.entity.Member;

public interface MemberService {

	MemberDTO save(MemberDTO dto);
	
	MemberDTO update(Long id, MemberDTO dto);
	
	List<MemberDTO> getAll();
	
	Member getById(Long id);
	
}
