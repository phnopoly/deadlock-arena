package com.deadlockArena.backEnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deadlockArena.backEnd.entity.Champion;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public interface ChampionRepository
		extends JpaRepository<Champion, String>, JpaSpecificationExecutor<Champion> {

	List<Champion> findAll();

	Optional<Champion> findById(int id);

	Optional<Champion> findByName(String name);
}
