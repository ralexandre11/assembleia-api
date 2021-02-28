package com.ribeiro.assembleiaapi.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ribeiro.assembleiaapi.Service.MemberService;
import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.model.entity.Member;
import com.ribeiro.assembleiaapi.model.mappers.MemberMapper;
import com.ribeiro.assembleiaapi.model.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Override
	public MemberDTO save(MemberDTO dto) {
		try {
			Member member = MemberMapper.fromDTO(dto);

			Member memberSaved = memberRepository.save(member);
			return MemberMapper.toDTO(memberSaved);
		} catch (ApiException a) {
			throw new ApiException(a.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public MemberDTO update(Long id, MemberDTO dto) {
		try {
			getById(id);
			Member member = MemberMapper.fromDTO(dto);
			member.setId(id);
			Member memberSaved = memberRepository.save(member);
			return MemberMapper.toDTO(memberSaved);
		} catch (ApiException a) {
			throw new ApiException(a.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ApiException("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			getById(id);
			memberRepository.deleteById(id);
		} catch (ApiException a) {
			throw new ApiException(a.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ApiException("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MemberDTO> getAll() {
		try {
			List<Member> members = memberRepository.findAllByOrderByIdAsc();
			return MemberMapper.toDtoList(members);
		} catch (Exception e) {
			throw new ApiException("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Member getById(Long id) {
		try {
			Optional<Member> member = memberRepository.findById(id);
			if (member.isPresent()) {
				return member.get();
			}
			throw new ApiException("Member Not Found.", HttpStatus.NOT_FOUND);
		} catch (ApiException a) {
			throw new ApiException(a.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ApiException("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
