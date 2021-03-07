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

import com.ribeiro.assembleiaapi.exception.ApiException;
import com.ribeiro.assembleiaapi.exception.ApiExceptionController;
import com.ribeiro.assembleiaapi.model.dto.MemberDTO;
import com.ribeiro.assembleiaapi.resource.dto.ResponseDTO;
import com.ribeiro.assembleiaapi.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

/**
 * Class responsible for making the endpoint available: "/member"
 * @author Ricardo Ribeiro (https://www.linkedin.com/in/ricardoalexandreribeiro/)
 * @since 01/03/2021
 *
 */
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService service;

	/**
	 * Method to return all Members
	 * @return ListList<MemberDTO> 
	 */
	@GetMapping
	@Operation(summary = "Return the list of all members")
	public ResponseEntity<List<MemberDTO>> getAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	/**
	 * Method to create new member
	 * @param dto
	 * @return ResponseDTO
	 */
	@PostMapping
	@Operation(summary = "Add a new member")
	public ResponseEntity<ResponseDTO> createMember(@RequestBody MemberDTO dto) {
		try {
			MemberDTO dtoSaved = service.save(dto);
			ResponseDTO response = new ResponseDTO("Created Member! ID: " + dtoSaved.getId());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (ApiExceptionController e) {
			ResponseDTO response = new ResponseDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response );
		}
	}

	/**
	 * Method for updating a member by ID
	 * @param id
	 * @param dto
	 * @return ResponseDTO
	 */
	@PutMapping("/{id}")
	@Operation(summary = "Update a member")
	public ResponseEntity<ResponseDTO> updateMember(
			@Parameter(description = "Member ID") 
			@PathVariable("id") Long id, @RequestBody MemberDTO dto) {
		try {
			MemberDTO dtoSaved = service.update(id, dto);
			ResponseDTO response = new ResponseDTO("Updated Member! ID: " + dtoSaved.getId());
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (ApiException e) {
			ResponseDTO response = new ResponseDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} catch (ApiExceptionController e) {
			ResponseDTO response = new ResponseDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	/**
	 * Method for deleting an existing Member by ID
	 * @param id
	 * @return ResponseDTO
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a member")
	public ResponseEntity<ResponseDTO> deleteMember(
			@Parameter(description = "Member ID") 
			@PathVariable("id") Long id) {
		try {
			service.delete(id);
			ResponseDTO response = new ResponseDTO("Deleted Member!");
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (ApiException e) {
			ResponseDTO response = new ResponseDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} catch (ApiExceptionController e) {
			ResponseDTO response = new ResponseDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response );
		}
	}


}
