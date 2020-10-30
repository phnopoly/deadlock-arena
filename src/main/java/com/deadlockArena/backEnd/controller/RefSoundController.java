package com.deadlockArena.backEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deadlockArena.backEnd.dto.RefSoundDto;
import com.deadlockArena.backEnd.service.Microservice;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Provides REST-API information for Reference Sound model show in SwaggerUI.
 *
 * @author zsaordenio
 *
 */
@RestController
@RequestMapping("/rest")
public class RefSoundController {

	@Autowired
	private Microservice microservice;

	@ApiOperation(value = "Get All Ref Sounds.", response = RefSoundDto.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Ref Sounds."),
			@ApiResponse(code = 404, message = "Ref Sounds do not exsit.") })
	@GetMapping("/allRefSounds")
	public List<RefSoundDto> getAllRefSounds() {
		return this.microservice.loadAllRefSounds();
	}

}
