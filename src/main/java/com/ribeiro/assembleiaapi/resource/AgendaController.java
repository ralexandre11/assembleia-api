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

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

/**
 * Class responsible for making the endpoint available: "/agenda"
 * @author Ricardo Ribeiro (https://www.linkedin.com/in/ricardoalexandreribeiro/)
 * @since 01/03/2021
 *
 */
@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

	private final AgendaServiceImpl service;

	/**
	 * Method to return all Agendas
	 * @return List<AgendaDTO>
	 */
	@GetMapping
	@Operation(summary = "Retorna a lista de todas Pautas")
	public ResponseEntity<List<AgendaDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	/**
	 * Method to create a new Agenda
	 * @param dto
	 * @return
	 */
	@PostMapping
	@Operation(summary = "Cria uma nova pauta")
	public ResponseEntity<ResponseDTO> createAgenda(@RequestBody AgendaDTO dto) {
		AgendaDTO dtoSaved = service.save(dto);
		ResponseDTO response = new ResponseDTO("Created Agenda! ID: " + dtoSaved.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * Method to open a new Agenda Session with an expiration date
	 * @param id
	 * @param dto
	 * @return
	 */
	@PutMapping("/{id}")
	@Operation(summary = "Abre uma sessão para votação de uma determinada pauta")
	public ResponseEntity<ResponseDTO> openSessionAgenda(@PathVariable("id") Long id, @RequestBody AgendaDTO dto) {
		AgendaDTO dtoSaved = service.update(id, dto);
		ResponseDTO response = new ResponseDTO("Opened Session! ID: " + dtoSaved.getId());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
