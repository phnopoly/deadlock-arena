package com.deadlockArena.backEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deadlockArena.backEnd.entity.Player;
import com.deadlockArena.backEnd.service.ServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest")
public class PlayerController {

	@Autowired
	private ServiceImpl serviceImpl;

	@ApiOperation(value = "Retrieve login info.", response = Player.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully logged in.") ,
			@ApiResponse(code = 404, message = "Username and Password are not found.") })
	@GetMapping("/login")
	public Player getPlayerForLogin(String username, String password) {
		return serviceImpl.getPlayerForLogin(username, password);
	}

	@ApiOperation(value = "Get all Players.", response = Player.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved all Players.") ,
			@ApiResponse(code = 404, message = "No players are present.") })
	@GetMapping("/allPlayers")
	public List<Player> getAllPlayers() {
		return serviceImpl.getAllPlayers();
	}

	@ApiOperation(value = "Create a new Player.", response = Player.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created Player.") ,
			@ApiResponse(code = 400, message = "Username already exsits.") })
	@PostMapping("/player")
	public Player newPlayer(@RequestBody Player Player) {
		return serviceImpl.newPlayer(Player);
	}
}
