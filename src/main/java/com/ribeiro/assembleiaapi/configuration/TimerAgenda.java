package com.ribeiro.assembleiaapi.configuration;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ribeiro.assembleiaapi.model.entity.Agenda;
import com.ribeiro.assembleiaapi.model.repository.AgendaRepository;
import com.ribeiro.assembleiaapi.service.impl.AgendaKafkaServiceImpl;

@Configuration
@EnableScheduling
public class TimerAgenda implements TimerInterface{
	
	@Autowired
	AgendaRepository agendaRepository;
	
	@Autowired
	AgendaKafkaServiceImpl voteKafkaServiceImpl;

	@Override
	@Scheduled(fixedDelay=MINUTE, initialDelay=30*SECOND)
	public void execute() {
		List<Agenda> expired = agendaRepository.findByFinished(false)
								.stream()
								.filter(a -> a.getExpiration().before(new Date()))
								.collect(Collectors.toList());
		for (Agenda agenda : expired) {
			agenda.setFinished(true);
			agendaRepository.save(agenda);
			voteKafkaServiceImpl.send(agenda);
		}
		
	}


}
