package com.deadlockarena.backend.dto;

import java.io.Serializable;

import com.deadlockarena.backend.entity.Player;
import com.deadlockarena.backend.mapper.PlayerMapper;

import lombok.Data;

/**
 * Maps to {@link Player} using {@link PlayerMapper}.
 *
 * @author zsaordenio
 *
 */
@Data
public class PlayerDto implements Serializable {
	private static final long serialVersionUID = 1572821508915977597L;

	private Long playerId;

	private String username;

	private String password;

	private String email;
}
