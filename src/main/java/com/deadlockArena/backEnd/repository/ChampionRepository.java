package com.deadlockArena.backEnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.deadlockArena.backEnd.entity.Champion;

/**
 * Spring Data Repository for {@link Champion} entity
 *
 * @author zsaordenio
 *
 */
@Repository
public interface ChampionRepository
extends JpaRepository<Champion, Long>, JpaSpecificationExecutor<Champion> {

	@Override
	List<Champion> findAll();

	Optional<Champion> findByChampionId(Long championId);

	Optional<Champion> findByName(String name);
}
