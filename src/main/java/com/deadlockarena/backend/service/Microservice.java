package com.deadlockarena.backend.service;

import java.util.List;

import com.deadlockarena.backend.dto.ChampionDto;
import com.deadlockarena.backend.dto.PlayerDto;
import com.deadlockarena.backend.dto.RefMusicDto;
import com.deadlockarena.backend.dto.RefPictureDto;
import com.deadlockarena.backend.dto.RefSoundDto;

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

	public List<RefSoundDto> loadAllRefSounds();

	public List<RefMusicDto> loadAllRefMusic();

}
