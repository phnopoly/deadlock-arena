package com.deadlockArena.backEnd.service;

import java.util.List;

import com.deadlockArena.backEnd.dto.ChampionDto;
import com.deadlockArena.backEnd.dto.PlayerDto;
import com.deadlockArena.backEnd.dto.RefPictureDto;

/**
 * Service interface that defines the API's for reference data.
 *
 * @author zsaordenio
 *
 */
public interface Microservice {
	public ChampionDto getChampion(String champion);

	public List<ChampionDto> getAllChampions();

	public PlayerDto getPlayerForLogin(String u, String p);

	public List<PlayerDto> getAllPlayers();

	public PlayerDto newPlayer(PlayerDto playerDto);

	public List<RefPictureDto> loadAllRefPictures();

	public RefPictureDto loadRefPicture(String fileName);

}
