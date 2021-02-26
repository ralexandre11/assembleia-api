package com.ribeiro.assembleiaapi.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ribeiro.assembleiaapi.model.enums.VoteValue;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="vote")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435230854486082409L;

	@Id
	@Column(name = "id_vote")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "vote_value")
	private VoteValue voteValue;
	
	@NotNull
	@ManyToOne(cascade=CascadeType.PERSIST) @JoinColumn(name="id_agenda")
	private Agenda agenda;

	@NotNull
	@ManyToOne(cascade=CascadeType.PERSIST) @JoinColumn(name="id_member")
	private Member member;

}
