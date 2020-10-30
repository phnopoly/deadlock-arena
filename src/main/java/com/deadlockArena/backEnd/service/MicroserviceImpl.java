package com.deadlockArena.backEnd.service;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockArena.backEnd.dto.ChampionDto;
import com.deadlockArena.backEnd.dto.PlayerDto;
import com.deadlockArena.backEnd.dto.RefPictureDto;
import com.deadlockArena.backEnd.dto.RefSoundDto;
import com.deadlockArena.backEnd.entity.Champion;
import com.deadlockArena.backEnd.entity.Player;
import com.deadlockArena.backEnd.entity.RefPicture;
import com.deadlockArena.backEnd.entity.RefSound;
import com.deadlockArena.backEnd.exception.DeadlockException;
import com.deadlockArena.backEnd.mapper.ChampionMapper;
import com.deadlockArena.backEnd.mapper.PlayerMapper;
import com.deadlockArena.backEnd.mapper.RefPictureMapper;
import com.deadlockArena.backEnd.mapper.RefSoundMapper;
import com.deadlockArena.backEnd.repository.ChampionRepository;
import com.deadlockArena.backEnd.repository.PlayerRepository;
import com.deadlockArena.backEnd.repository.RefPictureRepository;
import com.deadlockArena.backEnd.repository.RefSoundRepository;

/**
 * Implementation of {@link Microservice} that defines the APIs for the
 * Reference Data.
 *
 * @author zsaordenio
 *
 */
@Service
@Transactional
public class MicroserviceImpl implements Microservice {

	@Autowired
	private ChampionRepository championRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private RefPictureRepository pictureRepository;

	@Autowired
	private RefSoundRepository soundRepository;

	private static final ChampionMapper CHAMPION_MAPPER = Mappers.getMapper(ChampionMapper.class);

	private static final PlayerMapper PLAYER_MAPPER = Mappers.getMapper(PlayerMapper.class);

	private static final RefPictureMapper REF_PICTURE_MAPPER = Mappers.getMapper(RefPictureMapper.class);

	private static final RefSoundMapper REF_SOUND_MAPPER = Mappers.getMapper(RefSoundMapper.class);

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
	public List<RefPictureDto> loadAllRefPictures() {
		try {
			final List<RefPicture> refPictureList = this.pictureRepository.findAll();
			if (!refPictureList.isEmpty()) {
				return MicroserviceImpl.REF_PICTURE_MAPPER.entitiyToDto(refPictureList);
			} else {
				throw new DeadlockException("Cannot find any Ref Pictures.", HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public RefPictureDto loadRefPicture(final String fileName) {
		try {
			final Optional<RefPicture> picture = this.pictureRepository.findByFileName(fileName);
			if (picture.isPresent()) {
				return MicroserviceImpl.REF_PICTURE_MAPPER.entitiyToDto(picture.get());
			} else {
				throw new DeadlockException("Cannot find Ref Picture.", HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public List<RefSoundDto> loadAllRefSounds() {
		try {
			final List<RefSound> refSoundList = this.soundRepository.findAll();
			if (!refSoundList.isEmpty()) {
				return MicroserviceImpl.REF_SOUND_MAPPER.entitiyToDto(refSoundList);
			} else {
				throw new DeadlockException("Cannot find any Ref Sounds.", HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

}
