package com.ribeiro.assembleiaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ribeiro.assembleiaapi.model.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
