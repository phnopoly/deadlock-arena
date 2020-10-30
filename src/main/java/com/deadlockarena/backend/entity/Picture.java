package com.deadlockarena.backend.entity;

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

import lombok.Data;

@Data
@Entity
@Table(name = "PICTURE", schema = "deadlock")
public class Picture implements Serializable{
	private static final long serialVersionUID = -5208391162261595014L;

	@Id
	@Column(name = "PICTURE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PICTURE_SEQ")
	@SequenceGenerator(name = "PICTURE_SEQ", sequenceName = "PICTURE")
	private Long pictureId;

	@NotNull
	@Column(name = "FILE_NAME")
	private String fileName;

	@NotNull
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "CONTENTS")
	private byte[] contents;
}


