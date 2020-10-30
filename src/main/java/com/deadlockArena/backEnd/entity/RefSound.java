package com.deadlockArena.backEnd.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.deadlockArena.backEnd.dto.RefSoundDto;
import com.deadlockArena.backEnd.mapper.RefSoundMapper;

import lombok.Data;

/**
 * Maps to {@link RefSoundDto} using {@link RefSoundMapper}.
 *
 * @author zsaordenio
 *
 */
@Data
@Entity
@Table(name = "SOUND", schema = "deadlock")
public class RefSound implements Serializable {
	private static final long serialVersionUID = -500546788373195054L;

	@Id
	@Column(name = "SOUND_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SOUND_SEQ")
	@SequenceGenerator(name = "SOUND_SEQ", sequenceName = "SOUND")
	private Long refSoundId;

	@NotNull
	@Column(name = "FILE_NAME")
	private String fileName;

	@NotNull
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "CONTENTS")
	private byte[] contents;
}

