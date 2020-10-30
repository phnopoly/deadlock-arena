package com.deadlockArena.backEnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.deadlockArena.backEnd.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long>, JpaSpecificationExecutor<Picture> {

	@Override
	List<Picture> findAll();

	Optional<Picture> findByFileName(String fileName);

}
