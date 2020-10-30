package com.deadlockArena.backEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deadlockArena.backEnd.dto.RefPictureDto;
import com.deadlockArena.backEnd.service.Microservice;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Provides REST-API information for Reference Picture model show in SwaggerUI.
 *
 * @author zsaordenio
 *
 */
@RestController
@RequestMapping("/rest")
public class RefPictureController {

	@Autowired
	private Microservice microservice;

	@ApiOperation(value = "Get All Ref Pictures.", response = RefPictureDto.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Ref Pictures."),
			@ApiResponse(code = 404, message = "Ref Pictures do not exsit.") })
	@GetMapping("/allRefPictures")
	public List<RefPictureDto> getAllRefPictures() {
		return this.microservice.loadAllRefPictures();
	}

	@ApiOperation(value = "Get Ref Picture by File Name.", response = RefPictureDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Ref Picture."),
			@ApiResponse(code = 404, message = "Ref Picture does not exsit.") })
	@GetMapping("/refPicture={fileName}")
	public RefPictureDto getRefPictureByFileName(final String fileName) {
		return this.microservice.loadRefPicture(fileName);
	}

}
