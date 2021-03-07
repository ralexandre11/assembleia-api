package com.ribeiro.assembleiaapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.model.entity.Member;
import com.ribeiro.assembleiaapi.model.mappers.MemberMapper;
import com.ribeiro.assembleiaapi.model.repository.MemberRepository;
import com.ribeiro.assembleiaapi.service.MemberService;

import lombok.AllArgsConstructor;

/**
 * Class that implements the Member service layer
 * @author Ricardo Ribeiro (https://www.linkedin.com/in/ricardoalexandreribeiro/)
 * @since 01/03/2021
 *
 */
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	
	private final MemberMapper memberMaper;

	/**
	 * Method to save a new Member
	 * @param dto
	 * @return MemberDTO
	 */
	@Override
	@Transactional
	public MemberDTO save(MemberDTO dto) {
		Member member = memberMaper.fromDTO(dto);

		Member memberSaved = memberRepository.save(member);
		return memberMaper.toDTO(memberSaved);
	}

	/**
	 * Method for updating an existing Member
	 * @param id
	 * @param dto
	 * @return MemberDTO
	 */
	@Override
	@Transactional
	public MemberDTO update(Long id, MemberDTO dto) {
		getById(id);
		Member member = memberMaper.fromDTO(dto);
		member.setId(id);
		Member memberSaved = memberRepository.save(member);
		return memberMaper.toDTO(memberSaved);
	}

	/**
	 * Method for deleting an existing Member
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		getById(id);

		memberRepository.deleteById(id);
	}

	/**
	 * Method to return all Members
	 * @return List<MemberDTO>
	 */
	@Override
	public List<MemberDTO> getAll() {
		List<Member> members = memberRepository.findAllByOrderByIdAsc();

		return memberMaper.toDtoList(members);
	}

	/**
	 * Method to return a Member by ID
	 */
	@Override
	public Member getById(Long id) {
		Optional<Member> member = memberRepository.findById(id);
		if (member.isPresent()) {
			return member.get();
		} else {
			throw new ApiException("Member Not Found");
		}
	}

	/**
	 * Method to return a Member by CPF
	 */
	@Override
	public Member getByCpf(Long cpf) {
		return memberRepository.findByCpf(cpf);
	}

}
