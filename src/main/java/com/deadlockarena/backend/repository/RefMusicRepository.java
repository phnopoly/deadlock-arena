package com.deadlockarena.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.deadlockarena.backend.entity.RefMusic;

/**
 * Spring Data Repository for {@link RefMusic} entity
 *
 * @author zsaordenio
 *
 */
@Repository
public interface RefMusicRepository extends JpaRepository<RefMusic, Long>, JpaSpecificationExecutor<RefMusic> {

	@Override
	List<RefMusic> findAll();

	Optional<RefMusic> findByFileName(String fileName);

}
