package com.ribeiro.assembleiaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.entity.Member;
import com.ribeiro.assembleiaapi.model.entity.Vote;
import com.ribeiro.assembleiaapi.model.enums.VoteEnum;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findByAgendaAndMember(Agenda agenda, Member member);

    @Query("SELECT COUNT(*) "
  		  + "FROM Vote AS v WHERE v.agenda.id = :idAgenda AND voteValue = :vote")
    Long countVotes(@Param("idAgenda") Long idAgenda, @Param("vote") VoteEnum vote );
    
}
