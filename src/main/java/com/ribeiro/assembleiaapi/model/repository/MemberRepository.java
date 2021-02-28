package com.ribeiro.assembleiaapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ribeiro.assembleiaapi.model.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	public List<Member> findAllByOrderByIdAsc();
	
}
