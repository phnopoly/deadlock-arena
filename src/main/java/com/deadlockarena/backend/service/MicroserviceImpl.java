package com.deadlockarena.backend.service;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockarena.backend.dto.ChampionDto;
import com.deadlockarena.backend.dto.PlayerDto;
import com.deadlockarena.backend.dto.RefMusicDto;
import com.deadlockarena.backend.dto.RefPictureDto;
import com.deadlockarena.backend.dto.RefSoundDto;
import com.deadlockarena.backend.entity.Champion;
import com.deadlockarena.backend.entity.Player;
import com.deadlockarena.backend.entity.RefMusic;
import com.deadlockarena.backend.entity.RefPicture;
import com.deadlockarena.backend.entity.RefSound;
import com.deadlockarena.backend.exception.DeadlockException;
import com.deadlockarena.backend.mapper.ChampionMapper;
import com.deadlockarena.backend.mapper.PlayerMapper;
import com.deadlockarena.backend.mapper.RefMusicMapper;
import com.deadlockarena.backend.mapper.RefPictureMapper;
import com.deadlockarena.backend.mapper.RefSoundMapper;
import com.deadlockarena.backend.repository.ChampionRepository;
import com.deadlockarena.backend.repository.PlayerRepository;
import com.deadlockarena.backend.repository.RefMusicRepository;
import com.deadlockarena.backend.repository.RefPictureRepository;
import com.deadlockarena.backend.repository.RefSoundRepository;

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
	private RefPictureRepository refPictureRepository;

	@Autowired
	private RefSoundRepository refSoundRepository;

	@Autowired
	private RefMusicRepository refMusicRepository;

	private static final ChampionMapper CHAMPION_MAPPER = Mappers.getMapper(ChampionMapper.class);

	private static final PlayerMapper PLAYER_MAPPER = Mappers.getMapper(PlayerMapper.class);

	private static final RefPictureMapper REF_PICTURE_MAPPER = Mappers.getMapper(RefPictureMapper.class);

	private static final RefSoundMapper REF_SOUND_MAPPER = Mappers.getMapper(RefSoundMapper.class);

	private static final RefMusicMapper REF_MUSIC_MAPPER = Mappers.getMapper(RefMusicMapper.class);

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
			final List<RefPicture> refPictureList = this.refPictureRepository.findAll();
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
			final Optional<RefPicture> picture = this.refPictureRepository.findByFileName(fileName);
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
			final List<RefSound> refSoundList = this.refSoundRepository.findAll();
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

	@Override
	public List<RefMusicDto> loadAllRefMusic() {
		try {
			final List<RefMusic> refMusicList = this.refMusicRepository.findAll();
			if (!refMusicList.isEmpty()) {
				return MicroserviceImpl.REF_MUSIC_MAPPER.entitiyToDto(refMusicList);
			} else {
				throw new DeadlockException("Cannot find any Ref Musics.", HttpStatus.NOT_FOUND);
			}
		} catch (final DeadlockException e) {
			throw e;
		} catch (final Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

}
