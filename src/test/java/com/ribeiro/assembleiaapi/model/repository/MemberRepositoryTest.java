package com.ribeiro.assembleiaapi.model.repository;

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

import com.ribeiro.assembleiaapi.model.entity.Member;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	public static Member createTestMember() {
		return Member.builder().cpf(48721151040L).name("Member 01").build();
	}

	@Test
	void givenCpfAndName_WhenSaveMember_ThenIdIsNotNull() {
		//Given
		Member member = createTestMember();
		
		//When
		Member memberSaved = repository.save(member);
		
		//Then
		Assertions.assertThat(memberSaved.getId()).isNotNull();
	}

	@Test
	void givenMemberSaved_WhenFindByCpf_ThenMemberIsNotNull() {
		//Given
		Member member = createTestMember();
		testEntityManager.persist(member);

		//When
		Member memberFound = repository.findByCpf(member.getCpf());

		//Then
		Assertions.assertThat(memberFound).isNotNull();
	}

}
