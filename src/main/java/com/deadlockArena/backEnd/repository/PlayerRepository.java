package com.deadlockArena.backEnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.deadlockArena.backEnd.entity.Player;

/**
 * Spring Data Repository for {@link Player} entity
 *
 * @author zsaordenio
 *
 */
@Repository
public interface PlayerRepository
extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {

	@Override
	List<Player> findAll();

	Optional<Player> findByUsername(String username);

	Optional<Player> findByEmail(String email);

	Optional<Player> findByUsernameAndPassword(String username, String password);

}
