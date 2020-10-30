package com.deadlockArena.backEnd.dto;

import java.io.Serializable;

import com.deadlockArena.backEnd.entity.RefPicture;
import com.deadlockArena.backEnd.mapper.RefPictureMapper;

import lombok.Data;

/**
 * Maps to {@link RefPicture} using {@link RefPictureMapper}.
 *
 * @author zsaordenio
 *
 */
@Data
public class RefPictureDto implements Serializable{
	private static final long serialVersionUID = 6363556119094198298L;

	private Long refPictureId;

	private String fileName;

	private byte[] contents;
}


