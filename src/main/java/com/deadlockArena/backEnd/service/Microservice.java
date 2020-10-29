package com.deadlockArena.backEnd.service;

import java.util.List;

import com.deadlockArena.backEnd.dto.ChampionDto;
import com.deadlockArena.backEnd.dto.PlayerDto;

public interface Microservice {
	public ChampionDto getChampion(String champion);

	public List<ChampionDto> getAllChampions();

	public PlayerDto getPlayerForLogin(String u, String p);

	public List<PlayerDto> getAllPlayers();

	public PlayerDto newPlayer(final PlayerDto playerDto);

}
