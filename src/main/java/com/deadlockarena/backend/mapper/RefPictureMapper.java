package com.deadlockarena.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockarena.backend.dto.RefPictureDto;
import com.deadlockarena.backend.entity.RefPicture;

/**
 * Maps {@link RefPicture} to and from {@link RefPictureDto}. Mapper Implementation is
 * generated on build.
 *
 * @author zsaordenio
 *
 */
@Mapper(componentModel = "spring")
public interface RefPictureMapper {

	RefPictureDto entitiyToDto(final RefPicture refPicture);

	RefPicture dtoToEntity(final RefPictureDto refPictureDto);

	List<RefPictureDto> entitiyToDto(final List<RefPicture> refPictures);

	List<RefPicture> dtoToEntity(final List<RefPictureDto> refPictureDtos);

}
