package com.deadlockArena.backEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deadlockArena.backEnd.dto.RefMusicDto;
import com.deadlockArena.backEnd.service.Microservice;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Provides REST-API information for Reference Music model show in SwaggerUI.
 *
 * @author zsaordenio
 *
 */
@RestController
@RequestMapping("/rest")
public class RefMusicController {

	@Autowired
	private Microservice microservice;

	@ApiOperation(value = "Get All Ref Musics.", response = RefMusicDto.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Ref Music."),
			@ApiResponse(code = 404, message = "Ref Music do not exsit.") })
	@GetMapping("/allRefMusic")
	public List<RefMusicDto> getAllRefMusics() {
		return this.microservice.loadAllRefMusic();
	}

}
