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
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;

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

	@PostMapping
	public ResponseEntity<ResponseDTO> createAgenda(@RequestBody AgendaDTO dto) {
		AgendaDTO dtoSaved = service.save(dto);
		ResponseDTO response = new ResponseDTO("Created Agenda! ID: " + dtoSaved.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> openSessionAgenda(@PathVariable("id") Long id, @RequestBody AgendaDTO dto) {
		AgendaDTO dtoSaved = service.update(id, dto);
		ResponseDTO response = new ResponseDTO("Opened Session! ID: " + dtoSaved.getId());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
