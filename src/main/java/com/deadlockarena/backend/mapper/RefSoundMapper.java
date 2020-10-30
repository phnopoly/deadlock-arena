package com.deadlockarena.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockarena.backend.dto.RefSoundDto;
import com.deadlockarena.backend.entity.RefSound;

/**
 * Maps {@link RefSound} to and from {@link RefSoundDto}. Mapper Implementation
 * is generated on build.
 *
 * @author zsaordenio
 *
 */
@Mapper(componentModel = "spring")
public interface RefSoundMapper {

	RefSoundDto entitiyToDto(final RefSound refSound);

	RefSound dtoToEntity(final RefSoundDto refSoundDto);

	List<RefSoundDto> entitiyToDto(final List<RefSound> refSounds);

	List<RefSound> dtoToEntity(final List<RefSoundDto> refSoundDtos);

}
