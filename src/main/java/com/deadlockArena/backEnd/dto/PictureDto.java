package com.deadlockArena.backEnd.dto;

import java.io.Serializable;

import com.deadlockArena.backEnd.entity.Picture;
import com.deadlockArena.backEnd.mapper.PictureMapper;

import lombok.Data;

/**
 * Maps to {@link Picture} using {@link PictureMapper}.
 *
 * @author zsaordenio
 *
 */
@Data
public class PictureDto implements Serializable{
	private static final long serialVersionUID = 6363556119094198298L;

	private Long pictureId;

	private String fileName;

	private byte[] contents;
}


