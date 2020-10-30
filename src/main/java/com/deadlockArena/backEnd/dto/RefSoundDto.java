package com.deadlockArena.backEnd.dto;

import java.io.Serializable;

import com.deadlockArena.backEnd.entity.RefSound;
import com.deadlockArena.backEnd.mapper.RefSoundMapper;

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


