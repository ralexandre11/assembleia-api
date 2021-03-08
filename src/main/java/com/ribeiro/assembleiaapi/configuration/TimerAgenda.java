package com.ribeiro.assembleiaapi.configuration;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.repository.AgendaRepository;
import com.ribeiro.assembleiaapi.service.AgendaService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class TimerAgenda implements TimerInterface{
	
	private final AgendaRepository agendaRepository;
	
	private final AgendaService agendaService;
	

	@Override
	@Scheduled(fixedDelay=MINUTE, initialDelay=5*SECOND)
	public void execute() {
		Logger.getLogger(this.getClass().getName()).info("execute");
		List<Agenda> expiredAgendaList = agendaRepository.findByFinished(false)
								.stream()
								.filter(a -> a.getExpiration().before(new Date()))
								.collect(Collectors.toList());
		for (Agenda agenda : expiredAgendaList){
			agendaService.updateFinishedAgenda(agenda);
		}
		Logger.getLogger(this.getClass().getName()).info("finished");
	}


}
