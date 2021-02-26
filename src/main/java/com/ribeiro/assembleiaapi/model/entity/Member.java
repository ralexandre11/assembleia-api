package com.ribeiro.assembleiaapi.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="member")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1490624300288086256L;

	@Id
	@Column(name = "id_member")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "cpf")
	private Long cpf;

}