package com.deadlockArena.backEnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockArena.backEnd.entity.Player;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface PlayerRepository
		extends JpaRepository<Player, String>, JpaSpecificationExecutor<Player> {

	List<Player> findAll();

	Optional<Player> findByPlayerId(int playerId);

	Optional<Player> findByUsername(String username);

	Optional<Player> findByUsernameAndPassword(String username, String password);

}
