package com.ribeiro.assembleiaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ribeiro.assembleiaapi.model.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
