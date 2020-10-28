package com.deadlockArena.backEnd.service;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockArena.backEnd.entity.Champion;
import com.deadlockArena.backEnd.entity.Player;
import com.deadlockArena.backEnd.mapper.ChampionMapper;
import com.deadlockArena.backEnd.mapper.PlayerMapper;
import com.deadlockArena.backEnd.repository.ChampionRepository;
import com.deadlockArena.backEnd.repository.PlayerRepository;
import com.deadlockArena.dto.ChampionDto;
import com.deadlockArena.dto.PlayerDto;
import com.deadlockArena.exception.DeadlockException;

@Service
@Transactional
public class MicroserviceImpl implements Microservice {

	@Autowired
	private ChampionRepository championRepository;

	@Autowired
	private PlayerRepository playerRepository;

	private static final ChampionMapper CHAMPION_MAPPER = Mappers.getMapper(ChampionMapper.class);

	private static final PlayerMapper PLAYER_MAPPER = Mappers.getMapper(PlayerMapper.class);

	public ChampionDto getChampion(String champion) {
		champion = champion.trim();
		champion = champion.substring(0, 1).toUpperCase() + champion.substring(1).toLowerCase();
		try {
			Optional<Champion> c = championRepository.findByName(champion);
			if (c.isPresent()) {
				return CHAMPION_MAPPER.entitiyToDto(c.get());
			} else {
				throw new DeadlockException("Cannot find Champion.", HttpStatus.NOT_FOUND);
			}
		} catch (DeadlockException e) {
			throw e;
		} catch (Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	public List<ChampionDto> getAllChampions() {
		try {
			List<Champion> championList = championRepository.findAll();
			if (!championList.isEmpty()) {
				return CHAMPION_MAPPER.entitiyToDto(championList);
			} else {
				throw new DeadlockException("Cannot find any Champions.", HttpStatus.NOT_FOUND);
			}
		} catch (DeadlockException e) {
			throw e;
		} catch (Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	public PlayerDto getPlayerForLogin(String u, String p) {
		try {
			Optional<Player> player = playerRepository.findByUsernameAndPassword(u, p);
			if (!player.isPresent()) {
				return PLAYER_MAPPER.entitiyToDto(player.get());
			} else {
				String msg = "Cannot find player with username and password.";
				throw new DeadlockException(msg, HttpStatus.NOT_FOUND);
			}
		} catch (DeadlockException e) {
			throw e;
		} catch (Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	public List<PlayerDto> getAllPlayers() {
		try {
			List<Player> playerList = playerRepository.findAll();
			if (!playerList.isEmpty()) {
				return PLAYER_MAPPER.entitiyToDto(playerList);
			} else {
				throw new DeadlockException("Cannot find any Players.", HttpStatus.NOT_FOUND);
			}
		} catch (DeadlockException e) {
			throw e;
		} catch (Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	public PlayerDto newPlayer(final PlayerDto playerDto) {
		try {
			Optional<Player> p1 = this.playerRepository.findByUsername(playerDto.getUsername());
			Optional<Player> p2 = this.playerRepository.findByEmail(playerDto.getEmail());
			if (!p1.isPresent() && !p2.isPresent()) {
				Player toSavePlayer = PLAYER_MAPPER.dtoToEntity(playerDto);
				Player savedPlayer = this.playerRepository.save(toSavePlayer);
				return PLAYER_MAPPER.entitiyToDto(savedPlayer);
			} else if (!p1.isPresent() && p2.isPresent()) {
				throw new DeadlockException("Email Already Exists.", HttpStatus.BAD_REQUEST);
			} else if (p1.isPresent() && !p2.isPresent()) {
				throw new DeadlockException("Username Already Exists.", HttpStatus.BAD_REQUEST);
			} else {
				throw new DeadlockException("Username and Email Already Exists.",
						HttpStatus.BAD_REQUEST);
			}
		} catch (DeadlockException e) {
			throw e;
		} catch (Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

}
