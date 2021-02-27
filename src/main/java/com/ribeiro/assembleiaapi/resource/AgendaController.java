package com.ribeiro.assembleiaapi.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
//	@GetMapping
//	public ResponseEntity<List<AgendaDTO>> all() {
//		return ResponseEntity.status(HttpStatus.OK).body(service.all());
//	}

//	@PostMapping
//	public ResponseEntity<AgendaDTO> createDiscipline(@RequestBody Discipline discipline) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(discipline));
//	}
	
	@PostMapping
	public ResponseEntity<AgendaDTO> saveAgenda(@RequestBody AgendaDTO dto ) {
//		try {
			AgendaDTO dtoSaved = service.save(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(dtoSaved);
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
	}

	
}
