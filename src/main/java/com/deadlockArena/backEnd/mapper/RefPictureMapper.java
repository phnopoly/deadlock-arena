package com.deadlockArena.backEnd.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockArena.backEnd.dto.RefPictureDto;
import com.deadlockArena.backEnd.entity.RefPicture;

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
