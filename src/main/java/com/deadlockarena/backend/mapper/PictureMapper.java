package com.deadlockarena.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockarena.backend.dto.PictureDto;
import com.deadlockarena.backend.entity.Picture;

@Mapper(componentModel = "spring")
public interface PictureMapper {

	PictureDto entitiyToDto(final Picture picture);

	Picture dtoToEntity(final PictureDto pictureDto);

	List<PictureDto> entitiyToDto(final List<Picture> pictures);

	List<Picture> dtoToEntity(final List<PictureDto> pictureDtos);

}
