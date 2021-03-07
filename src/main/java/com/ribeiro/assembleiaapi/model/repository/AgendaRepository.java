package com.ribeiro.assembleiaapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ribeiro.assembleiaapi.model.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	public List<Agenda> findByFinished(boolean value);
	
}
