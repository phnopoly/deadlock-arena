package com.deadlockarena.backend.dto;

import java.io.Serializable;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class BaseDto implements Serializable {
	private static final long serialVersionUID = 330772425255049735L;

	@LastModifiedDate
	@UpdateTimestamp
	private String updatedDateTime;

	@CreatedDate
	@CreationTimestamp
	private String createdDateTime;

}
