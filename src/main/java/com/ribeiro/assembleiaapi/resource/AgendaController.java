package com.ribeiro.assembleiaapi.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.assembleiaapi.Service.impl.AgendaServiceImpl;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

	private final AgendaServiceImpl service;

	@GetMapping
	public ResponseEntity<List<AgendaDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

//	@PostMapping
//	public ResponseEntity<AgendaDTO> createDiscipline(@RequestBody Discipline discipline) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(discipline));
//	}

	@PostMapping
	public ResponseEntity<String> createAgenda(@RequestBody AgendaDTO dto) {
		service.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Created Agenda!");
	}

	@PutMapping("/{id}")
	public ResponseEntity<AgendaDTO> openSessionAgenda(@PathVariable("id") Long id, @RequestBody AgendaDTO dto) {
		AgendaDTO dtoSaved = service.update(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(dtoSaved);
	}

}
