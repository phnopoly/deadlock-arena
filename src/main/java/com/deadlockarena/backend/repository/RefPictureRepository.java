package com.deadlockarena.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.deadlockarena.backend.entity.RefPicture;

/**
 * Spring Data Repository for {@link RefPicture} entity
 *
 * @author zsaordenio
 *
 */
@Repository
public interface RefPictureRepository extends JpaRepository<RefPicture, Long>, JpaSpecificationExecutor<RefPicture> {

	@Override
	List<RefPicture> findAll();

	Optional<RefPicture> findByFileName(String fileName);

}
