package com.deadlockarena.backend.dto;

import java.io.Serializable;

import com.deadlockarena.backend.entity.RefMusic;
import com.deadlockarena.backend.mapper.RefMusicMapper;

import lombok.Data;

/**
 * Maps to {@link RefMusic} using {@link RefMusicMapper}.
 *
 * @author zsaordenio
 *
 */
@Data
public class RefMusicDto implements Serializable {
	private static final long serialVersionUID = 6363556119094198298L;

	private Long refMusicId;

	private String fileName;

	private byte[] contents;
}

