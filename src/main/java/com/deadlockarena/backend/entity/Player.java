package com.deadlockarena.backend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.deadlockarena.backend.dto.PlayerDto;
import com.deadlockarena.backend.mapper.PlayerMapper;

import lombok.Data;

/**
 * Maps to {@link PlayerDto} using {@link PlayerMapper}.
 *
 * @author zsaordenio
 *
 */
@Data
@Entity
@Table(name = "PLAYER", schema = "deadlock")
public class Player implements Serializable {
	private static final long serialVersionUID = -2275053963480092839L;

	@Id
	@Column(name = "PLAYER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PLAYER_SEQ")
	@SequenceGenerator(name = "PLAYER_SEQ", sequenceName = "PLAYER_SEQ")
	private Long playerId;

	@NotNull
	@Column(name = "USERNAME")
	private String username;

	@NotNull
	@Column(name = "PASSWORD")
	private String password;

	@NotNull
	@Email
	@Column(name = "EMAIL")
	private String email;

}
