package com.ribeiro.assembleiaapi.Service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ribeiro.assembleiaapi.Service.AgendaService;
import com.ribeiro.assembleiaapi.Service.MemberService;
import com.ribeiro.assembleiaapi.Service.VoteService;
import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteResultDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.entity.Member;
import com.ribeiro.assembleiaapi.model.entity.Vote;
import com.ribeiro.assembleiaapi.model.enums.VoteEnum;
import com.ribeiro.assembleiaapi.model.mappers.VoteMapper;
import com.ribeiro.assembleiaapi.model.repository.VoteRepository;

/**
 * Class that implements the Vote service layer
 * @author Ricardo Ribeiro (https://www.linkedin.com/in/ricardoalexandreribeiro/)
 * @since 01/03/2021
 *
 */
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private VoteRepository voteRepository;
		
	/**
	 * Method for registering a new Vote by Member and Agenda
	 */
	@Override
	public void registerVote(VoteDTO voteDTO) {
		Agenda agenda = agendaService.getById(voteDTO.getIdAgenda());
		isOpenedAgenda(agenda);

		Member member = existCpf(voteDTO.getCpf());
		alredyVoteCpfAgenda(agenda, member);
		
		Vote vote = VoteMapper.fromDTO(voteDTO, agenda, member);
		voteRepository.save(vote);
	}

	/**
	 * Method to return a result all Votes by Agenda
	 */
	@Override
	public VoteResultDTO getResultVotes(Long idAgenda) {
		return VoteResultDTO.builder()
				.idAgenda(idAgenda)
				.sim(voteRepository.countVotes(idAgenda, VoteEnum.SIM))
				.nao(voteRepository.countVotes(idAgenda, VoteEnum.NAO))
				.build();
	}

	/**
	 * Method to check if Agenda is opened
	 * @param agenda
	 */
	private void isOpenedAgenda(Agenda agenda) {
		Date date = new Date();
		if(agenda.getExpiration() == null || agenda.getExpiration().after(date)) {
			throw new ApiException("Agenda is not Opened!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Method to verify if exist CPF
	 * @param cpf
	 * @return
	 */
	private Member existCpf(Long cpf) {
		Member member = memberService.getByCpf(cpf);
		if (member == null) {
			throw new ApiException("CPF not exist!", HttpStatus.BAD_REQUEST);
		}
		return member;
	}
	
	/**
	 * Method to check if the vote has alredy registered 
	 * @param agenda
	 * @param member
	 */
	private void alredyVoteCpfAgenda(Agenda agenda, Member member) {
		Vote vote = voteRepository.findByAgendaAndMember(agenda, member);
		if (vote != null) {
			throw new ApiException("This Member has already voted!", HttpStatus.BAD_REQUEST);
		}
	}
}
