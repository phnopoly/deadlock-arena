package com.deadlockArena.backEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deadlockArena.backEnd.entity.Champion;
import com.deadlockArena.backEnd.service.ServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest")
public class ChampionController {

	@Autowired
	private ServiceImpl serviceImpl;

	@ApiOperation(value = "Get champion by name.", response = Champion.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Champion.") ,
			@ApiResponse(code = 404, message = "Champion does not exsit.") })
	@GetMapping("/champion")
	public Champion getChampion(@RequestParam String champion) {
		return serviceImpl.getChampion(champion);
	}

	@ApiOperation(value = "Get all Champions.", response = Champion.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved all Champions.") ,
			@ApiResponse(code = 404, message = "Champions do not exist.") })
	@GetMapping("/allChampions")
	public List<Champion> getAllChampions() {
		return serviceImpl.getAllChampions();
	}

}
