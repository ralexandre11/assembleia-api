package com.ribeiro.assembleiaapi.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.assembleiaapi.Service.impl.MemberServiceImpl;
import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author ricardo
 * @since 01/03/2021
 *
 */
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberServiceImpl service;

	@GetMapping
	@Operation(summary = "Retorna a lista de todos os membros")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Lista de membros", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = MemberDTO.class)) }),
			  @ApiResponse(responseCode = "404", description = "Nenhum membro encontrado", 
			    content = @Content) })	
	public ResponseEntity<List<MemberDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	/**
	 * Method to create new member
	 * @param dto
	 * @return ResponseDTO
	 */
	@PostMapping
	public ResponseEntity<ResponseDTO> createMember(@RequestBody MemberDTO dto) {
		MemberDTO dtoSaved = service.save(dto);
		ResponseDTO response = new ResponseDTO("Created Member! ID: " + dtoSaved.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera os dados do membro")
	public ResponseEntity<ResponseDTO> updateMember(@Parameter(description = "identificador do membro") @PathVariable("id") Long id, @RequestBody MemberDTO dto) {
		MemberDTO dtoSaved = service.update(id, dto);
		ResponseDTO response = new ResponseDTO("Updated Member! ID: " + dtoSaved.getId());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteMember(@PathVariable("id") Long id) {
		service.delete(id);
		ResponseDTO response = new ResponseDTO("Deleted Member!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}


}
