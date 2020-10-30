package com.deadlockarena.backend.dto;

import java.io.Serializable;

import com.deadlockarena.backend.entity.RefSound;
import com.deadlockarena.backend.mapper.RefSoundMapper;

import lombok.Data;

/**
 * Maps to {@link RefSound} using {@link RefSoundMapper}.
 *
 * @author zsaordenio
 *
 */
@Data
public class RefSoundDto implements Serializable{
	private static final long serialVersionUID = 8551095983679985521L;

	private Long refSoundId;

	private String fileName;

	private byte[] contents;
}


