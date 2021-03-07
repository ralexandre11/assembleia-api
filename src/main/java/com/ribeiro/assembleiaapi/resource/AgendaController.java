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

import com.ribeiro.assembleiaapi.exception.ApiExceptionController;
import com.ribeiro.assembleiaapi.model.dto.AgendaAddDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaDTO;
import com.ribeiro.assembleiaapi.model.dto.AgendaExpirationDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;
import com.ribeiro.assembleiaapi.service.AgendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

	private final AgendaService service;

	/**
	 * Method to return all Agendas
	 * @return List<AgendaDTO>
	 */
	@GetMapping
	@Operation(summary = "Return a list of all Agendas")
	public ResponseEntity<List<AgendaDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	/**
	 * Method to create a new Agenda
	 * @param dto
	 * @return ResponseDTO
	 */
	@PostMapping
	@Operation(summary = "Create a new agenda")
	public ResponseEntity<ResponseDTO> createAgenda(
			@Parameter(description = "Description agenda")
			@RequestBody AgendaAddDTO agendaAddDto) {

		try {
			AgendaDTO dtoSaved = service.save(agendaAddDto);
			ResponseDTO response = new ResponseDTO("Created Agenda! ID: " + dtoSaved.getId());
	
			//TODO ++ add location header
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (ApiExceptionController e) {
			ResponseDTO response = new ResponseDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response );
		} catch (Exception e) {
			throw new ApiExceptionController("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Method to open a new Agenda Session with an expiration date
	 * @param id
	 * @param dto
	 * @return ResponseDTO
	 */
	@PutMapping("/{id}")
	@Operation(summary = "Open a new vote session updating the agenda")
	public ResponseEntity<ResponseDTO> openSessionAgenda(
			@PathVariable("id") Long id,
			@Parameter(description = "Expiration date agenda")
			@RequestBody AgendaExpirationDTO expiration) {
		try {
			AgendaDTO dtoSaved = service.update(id, AgendaDTO.builder().expiration(expiration.getExpiration()).build());
			//TODO no need to return ID, already in the path
			//TODO return expiration instead
			ResponseDTO response = new ResponseDTO("Opened Session! ID: " + dtoSaved.getId());
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (ApiExceptionController e) {
			ResponseDTO response = new ResponseDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response );
		} catch (Exception e) {
			throw new ApiExceptionController("Internal Error!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
