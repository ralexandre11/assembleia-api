package com.ribeiro.assembleiaapi.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.model.dto.CpfApiDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteDTO;
import com.ribeiro.assembleiaapi.model.dto.VoteResultDTO;
import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.entity.Member;
import com.ribeiro.assembleiaapi.model.entity.Vote;
import com.ribeiro.assembleiaapi.model.enums.VoteEnum;
import com.ribeiro.assembleiaapi.model.mappers.VoteMapper;
import com.ribeiro.assembleiaapi.model.repository.VoteRepository;
import com.ribeiro.assembleiaapi.service.AgendaService;
import com.ribeiro.assembleiaapi.service.CpfApiService;
import com.ribeiro.assembleiaapi.service.MemberService;
import com.ribeiro.assembleiaapi.service.VoteService;

import lombok.AllArgsConstructor;

/**
 * Class that implements the Vote service layer
 * @author Ricardo Ribeiro (https://www.linkedin.com/in/ricardoalexandreribeiro/)
 * @since 01/03/2021
 *
 */
@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

	private final VoteMapper voteMapper;
	
	private final AgendaService agendaService;
	
	private final MemberService memberService;
	
	private final VoteRepository voteRepository;
	
	private final CpfApiService cpfApiService;
	
	/**
	 * Method for registering a new Vote by Member and Agenda
	 */
	@Override
	@Transactional
	public void registerVote(VoteDTO voteDto) {
		Vote vote = validateVote(voteDto);
		
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
	 * Method to Validate Vote
	 * @param voteDto
	 * @return Vote
	 */
	private Vote validateVote(VoteDTO voteDto) {
		// Check if agenda is opened
		Agenda agenda = agendaService.getById(voteDto.getIdAgenda());
		isOpenedAgenda(agenda);

		// Check if member exist
		Member member = existCpf(voteDto.getCpf());
		
		// Call Api and Check if Cpf is able
		isCpfAble(voteDto.getCpf());
		
		// Check if member has already voted
		alredyVoteCpfAgenda(agenda, member);
		
		return voteMapper.fromDTO(voteDto, agenda, member);
	}
	

	/**
	 * Method to check if Agenda is opened
	 * @param agenda
	 */
	private void isOpenedAgenda(Agenda agenda) {
		if(agenda.getExpiration() == null || agenda.getExpiration().before(new Date())) {
			throw new ApiException("Agenda is not Opened!");
		}
	}

	/**
	 * Method to call api and check if CPF is able
	 * @param cpf
	 */
	private void isCpfAble(Long cpf) {
		CpfApiDTO cpfApiDto = cpfApiService.checkCpfApi(cpf);
		
		String status = cpfApiDto.getStatus();
		if (status.isEmpty() || status.equals("UNABLE_TO_VOTE")) {
			throw new ApiException("CPF is not able!");
		}
	}

	/**
	 * Method to verify if exist CPF
	 * @param cpf
	 * @return Member
	 */
	private Member existCpf(Long cpf) {
		Member member = memberService.getByCpf(cpf);
		if (member == null) {
			throw new ApiException("CPF not exist!");
		}
		return member;
	}
	
	/**
	 * Method to check if the vote has already registered 
	 * @param agenda
	 * @param member
	 */
	private void alredyVoteCpfAgenda(Agenda agenda, Member member) {
		Vote vote = voteRepository.findByAgendaAndMember(agenda, member);
		if (vote != null) {
			throw new ApiException("This Member has already voted!");
		}
	}
}
