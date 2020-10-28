package com.deadlockArena.backEnd.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PLAYER", schema = "DEADLOCK")
public class Player extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2275053963480092839L;

	@Id
	@Column(name = "PLAYER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
