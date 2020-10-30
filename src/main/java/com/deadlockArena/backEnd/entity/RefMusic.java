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

import com.deadlockArena.backEnd.dto.RefMusicDto;
import com.deadlockArena.backEnd.mapper.RefMusicMapper;

import lombok.Data;

/**
 * Maps to {@link RefMusicDto} using {@link RefMusicMapper}.
 *
 * @author zsaordenio
 *
 */
@Data
@Entity
@Table(name = "MUSIC", schema = "deadlock")
public class RefMusic implements Serializable {
	private static final long serialVersionUID = -5208391162261595014L;

	@Id
	@Column(name = "MUSIC_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MUSIC_SEQ")
	@SequenceGenerator(name = "MUSIC_SEQ", sequenceName = "MUSIC")
	private Long refMusicId;

	@NotNull
	@Column(name = "FILE_NAME")
	private String fileName;

	@NotNull
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "CONTENTS")
	private byte[] contents;
}

