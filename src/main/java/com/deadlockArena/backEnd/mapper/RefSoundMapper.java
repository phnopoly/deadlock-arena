package com.deadlockArena.backEnd.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockArena.backEnd.dto.RefSoundDto;
import com.deadlockArena.backEnd.entity.RefSound;

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
