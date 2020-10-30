package com.deadlockarena.backend.service;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockarena.backend.dto.ChampionDto;
import com.deadlockarena.backend.dto.PictureDto;
import com.deadlockarena.backend.dto.PlayerDto;
import com.deadlockarena.backend.entity.Champion;
import com.deadlockarena.backend.entity.Picture;
import com.deadlockarena.backend.entity.Player;
import com.deadlockarena.backend.exception.DeadlockException;
import com.deadlockarena.backend.mapper.ChampionMapper;
import com.deadlockarena.backend.mapper.PictureMapper;
import com.deadlockarena.backend.mapper.PlayerMapper;
import com.deadlockarena.backend.repository.ChampionRepository;
import com.deadlockarena.backend.repository.PictureRepository;
import com.deadlockarena.backend.repository.PlayerRepository;

@Service
@Transactional
public class MicroserviceImpl implements Microservice {

	@Autowired
	private ChampionRepository championRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private PictureRepository pictureRepository;

	private static final ChampionMapper CHAMPION_MAPPER = Mappers.getMapper(ChampionMapper.class);

	private static final PlayerMapper PLAYER_MAPPER = Mappers.getMapper(PlayerMapper.class);

	private static final PictureMapper PICTURE_MAPPER = Mappers.getMapper(PictureMapper.class);

	@Override
	public ChampionDto getChampion(String champion) {
		champion = champion.trim();
		champion = champion.substring(0, 1).toUpperCase() + champion.substring(1).toLowerCase();
		try {
			final Optional<Champion> c = this.championRepository.findByName(champion);
			if (c.isPresent()) {
				return MicroserviceImpl.CHAMPION_MAPPER.entitiyToDto(c.get());
			} else {
				throw new DeadlockException("Cannot find Champion.", HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public List<ChampionDto> getAllChampions() {
		try {
			final List<Champion> championList = this.championRepository.findAll();
			if (!championList.isEmpty()) {
				return MicroserviceImpl.CHAMPION_MAPPER.entitiyToDto(championList);
			} else {
				throw new DeadlockException("Cannot find any Champions.", HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public PlayerDto getPlayerForLogin(final String u, final String p) {
		try {
			final Optional<Player> player = this.playerRepository.findByUsernameAndPassword(u, p);
			if (!player.isPresent()) {
				return MicroserviceImpl.PLAYER_MAPPER.entitiyToDto(player.get());
			} else {
				final String msg = "Cannot find player with username and password.";
				throw new DeadlockException(msg, HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public List<PlayerDto> getAllPlayers() {
		try {
			final List<Player> playerList = this.playerRepository.findAll();
			if (!playerList.isEmpty()) {
				return MicroserviceImpl.PLAYER_MAPPER.entitiyToDto(playerList);
			} else {
				throw new DeadlockException("Cannot find any Players.", HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public PlayerDto newPlayer(final PlayerDto playerDto) {
		try {
			final Optional<Player> p1 = this.playerRepository.findByUsername(playerDto.getUsername());
			final Optional<Player> p2 = this.playerRepository.findByEmail(playerDto.getEmail());
			if (!p1.isPresent() && !p2.isPresent()) {
				final Player toSavePlayer = MicroserviceImpl.PLAYER_MAPPER.dtoToEntity(playerDto);
				final Player savedPlayer = this.playerRepository.save(toSavePlayer);
				return MicroserviceImpl.PLAYER_MAPPER.entitiyToDto(savedPlayer);
			} else if (!p1.isPresent() && p2.isPresent()) {
				throw new DeadlockException("Email Already Exists.", HttpStatus.BAD_REQUEST);
			} else if (p1.isPresent() && !p2.isPresent()) {
				throw new DeadlockException("Username Already Exists.", HttpStatus.BAD_REQUEST);
			} else {
				throw new DeadlockException("Username and Email Already Exists.", HttpStatus.BAD_REQUEST);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public List<PictureDto> loadAllPictures() {
		try {
			final List<Picture> pictureList = this.pictureRepository.findAll();
			if (!pictureList.isEmpty()) {
				return MicroserviceImpl.PICTURE_MAPPER.entitiyToDto(pictureList);
			} else {
				throw new DeadlockException("Cannot find any Pictures.", HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public PictureDto loadPicture(final String fileName) {
		try {
			final Optional<Picture> picture = this.pictureRepository.findByFileName(fileName);
			if (picture.isPresent()) {
				return MicroserviceImpl.PICTURE_MAPPER.entitiyToDto(picture.get());
			} else {
				throw new DeadlockException("Cannot find Picture.", HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

}
