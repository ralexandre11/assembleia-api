package com.ribeiro.assembleiaapi.Service;

import java.util.List;
import java.util.Optional;

import com.ribeiro.assembleiaapi.model.entity.Member;

public interface MemberService {

	Member save(Member member);
	
	Member update(Member member);
	
	void delete(Member member);
	
	List<Member> all();
	
	Optional<Member> getByID(Long id);
	
}
