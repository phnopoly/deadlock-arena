package com.deadlockarena.backend.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PictureDto implements Serializable{
	private static final long serialVersionUID = 6363556119094198298L;

	private Long pictureId;

	private String fileName;
}


