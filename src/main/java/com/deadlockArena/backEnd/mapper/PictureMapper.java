package com.deadlockArena.backEnd.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockArena.backEnd.dto.PictureDto;
import com.deadlockArena.backEnd.entity.Picture;

/**
 * Maps {@link Picture} to and from {@link PictureDto}. Mapper Implementation is
 * generated on build.
 *
 * @author zsaordenio
 *
 */
@Mapper(componentModel = "spring")
public interface PictureMapper {

	PictureDto entitiyToDto(final Picture picture);

	Picture dtoToEntity(final PictureDto pictureDto);

	List<PictureDto> entitiyToDto(final List<Picture> pictures);

	List<Picture> dtoToEntity(final List<PictureDto> pictureDtos);

}
