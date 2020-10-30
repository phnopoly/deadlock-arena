package com.deadlockarena.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deadlockarena.backend.dto.ChampionDto;
import com.deadlockarena.backend.entity.Champion;
import com.deadlockarena.backend.service.Microservice;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Provides REST-API information for Champion model show in SwaggerUI.
 *
 * @author zsaordenio
 *
 */
@RestController
@RequestMapping("/rest")
public class ChampionController {

	@Autowired
	private Microservice microservice;

	@ApiOperation(value = "Get champion by name.", response = Champion.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Champion.") ,
			@ApiResponse(code = 404, message = "Champion does not exsit.") })
	@GetMapping("/champion")
	public ChampionDto getChampion(@RequestParam final String champion) {
		return this.microservice.getChampion(champion);
	}

	@ApiOperation(value = "Get all Champions.", response = Champion.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved all Champions.") ,
			@ApiResponse(code = 404, message = "Champions do not exist.") })
	@GetMapping("/allChampions")
	public List<ChampionDto> getAllChampions() {
		return this.microservice.getAllChampions();
	}

}
