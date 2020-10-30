package com.deadlockArena.backEnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.deadlockArena.backEnd.entity.RefSound;

/**
 * Spring Data Repository for {@link RefSound} entity
 *
 * @author zsaordenio
 *
 */
@Repository
public interface RefSoundRepository extends JpaRepository<RefSound, Long>, JpaSpecificationExecutor<RefSound> {

	@Override
	List<RefSound> findAll();

	Optional<RefSound> findByFileName(String fileName);

}
