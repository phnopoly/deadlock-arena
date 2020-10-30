package com.deadlockarena.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deadlockarena.backend.dto.PictureDto;
import com.deadlockarena.backend.service.Microservice;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest")
public class PictureController {

	@Autowired
	private Microservice microservice;

	@ApiOperation(value = "Get All Pictures.", response = PictureDto.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Pictures."),
			@ApiResponse(code = 404, message = "Pictures do not exsit.") })
	@GetMapping("/allPictures")
	public List<PictureDto> getAllPictures() {
		return this.microservice.loadAllPictures();
	}

	@ApiOperation(value = "Get Picture by File Name.", response = PictureDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Picture."),
			@ApiResponse(code = 404, message = "Picture does not exsit.") })
	@GetMapping("/picture={fileName}")
	public PictureDto getPictureByFileName(final String fileName) {
		return this.microservice.loadPicture(fileName);
	}

}
