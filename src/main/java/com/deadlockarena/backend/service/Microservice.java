package com.deadlockarena.backend.service;

import java.util.List;

import com.deadlockarena.backend.dto.ChampionDto;
import com.deadlockarena.backend.dto.PictureDto;
import com.deadlockarena.backend.dto.PlayerDto;

public interface Microservice {
	public ChampionDto getChampion(String champion);

	public List<ChampionDto> getAllChampions();

	public PlayerDto getPlayerForLogin(String u, String p);

	public List<PlayerDto> getAllPlayers();

	public PlayerDto newPlayer(PlayerDto playerDto);

	public List<PictureDto> loadAllPictures();

	public PictureDto loadPicture(String fileName);

}
