package com.deadlockArena.backEnd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.deadlockArena.backEnd.entity.Champion;
import com.deadlockArena.backEnd.entity.Player;
import com.deadlockArena.backEnd.repository.ChampionRepository;
import com.deadlockArena.backEnd.repository.PlayerRepository;
import com.deadlockArena.exception.DeadlockException;

@Component
public class ServiceImpl {

	@Autowired
	private ChampionRepository championRepository;

	@Autowired
	private PlayerRepository playerRepository;

	public Champion getChampion(String champion) {
		champion = champion.trim();
		champion = champion.substring(0, 1).toUpperCase() + champion.substring(1).toLowerCase();
		try {
			Optional<Champion> c = championRepository.findByName(champion);
			if (c.isPresent()) {
				return c.get();
			} else {
				throw new DeadlockException("Cannot find Champion.", HttpStatus.NOT_FOUND);
			}
		} catch (DeadlockException e) {
			throw e;
		} catch (Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	public List<Champion> getAllChampions() {
		try {
			List<Champion> championList = championRepository.findAll();
			if (!championList.isEmpty()) {
				return championList;
			} else {
				throw new DeadlockException("Cannot find any Champions.", HttpStatus.NOT_FOUND);
			}
		} catch (DeadlockException e) {
			throw e;
		} catch (Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	public Player getPlayerForLogin(String u, String p) {
		try {
			Optional<Player> player = playerRepository.findByUsernameAndPassword(u, p);
			if (!player.isPresent()) {
				return player.get();
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

	public List<Player> getAllPlayers() {
		try {
			List<Player> playerList = playerRepository.findAll();
			if (!playerList.isEmpty()) {
				return playerList;
			} else {
				throw new DeadlockException("Cannot find any Players.", HttpStatus.NOT_FOUND);
			}
		} catch (DeadlockException e) {
			throw e;
		} catch (Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	public Player newPlayer(final Player player) {
		try {
			if (!this.playerRepository.findByUsername(player.getUsername()).isPresent()) {
				return this.playerRepository.save(player);
			} else {
				throw new DeadlockException("Username Already Exists.", HttpStatus.BAD_REQUEST);
			}
		} catch (DeadlockException e) {
			throw e;
		} catch (Exception e) {
			throw new DeadlockException("Misc Err.", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

}
