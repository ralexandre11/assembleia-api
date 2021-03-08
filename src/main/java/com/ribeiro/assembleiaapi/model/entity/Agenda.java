package com.ribeiro.assembleiaapi.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="agenda")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Agenda implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5521412661807712736L;

	@Id
	@Column(name="id_agenda")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	@Column(name="expiration_date")
	private Date expiration;

	@Column(name="finished", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean finished;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_agenda")
	private List<Vote> votes = new ArrayList<>();
	
}
