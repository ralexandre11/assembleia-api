package com.ribeiro.assembleiaapi.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ribeiro.assembleiaapi.model.entity.Agenda;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AgendaRepositoryIntegrationTest {

	@Autowired
	AgendaRepository repository;

	@Autowired
	TestEntityManager testEntityManager;

	@Test
	void givenDescription_whenSave_thenAgendaIsSaved() {
		// Given
		Agenda agenda = new Agenda();
		agenda.setDescription("my description");

		// When
		Agenda agendaSaved = repository.save(agenda);

		// Then
		Assertions.assertThat(agendaSaved.getId()).isNotNull();
		Assertions.assertThat(agendaSaved.getDescription()).isEqualTo("my description");
		Assertions.assertThat(agendaSaved.getExpiration()).isNull();
		Assertions.assertThat(agendaSaved.getVotes()).isEmpty();
	}

	@Test
	void givenExistingId_whenFindById_thenAgendaReturned() {
		Agenda agenda = new Agenda();
		agenda.setDescription("my description");
		Agenda agendaSaved = repository.save(agenda);
		testEntityManager.clear();

		Optional<Agenda> actual = repository.findById(agendaSaved.getId());

		Assertions.assertThat(actual).isPresent();
	}

	@Test
	void givenUnexistingId_whenFindById_thenEmptyReturned() {

		Optional<Agenda> agenda = repository.findById(1000L);

		Assertions.assertThat(agenda).isEmpty();
	}
}