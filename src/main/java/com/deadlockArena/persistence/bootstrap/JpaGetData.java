package com.deadlockArena.persistence.bootstrap;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deadlockArena.persistence.entity.Champion;
import com.deadlockArena.persistence.repository.ChampionRepository;

@Component
public class JpaGetData {

	@Autowired
	private ChampionRepository championRepository;

	public Optional<Champion> getChampion(String championString) {
		return championRepository.findByName(championString);
	}
}
