package com.deadlockArena.backEnd.dto;

import java.io.Serializable;

import com.deadlockArena.backEnd.entity.Player;
import com.deadlockArena.backEnd.mapper.PlayerMapper;

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
