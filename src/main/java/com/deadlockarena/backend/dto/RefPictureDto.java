package com.deadlockarena.backend.dto;

import java.io.Serializable;

import com.deadlockarena.backend.entity.RefPicture;
import com.deadlockarena.backend.mapper.RefPictureMapper;

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


