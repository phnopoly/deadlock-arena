package com.deadlockArena.backEnd.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockArena.backEnd.dto.RefMusicDto;
import com.deadlockArena.backEnd.entity.RefMusic;

/**
 * Maps {@link RefMusic} to and from {@link RefMusicDto}. Mapper Implementation
 * is generated on build.
 *
 * @author zsaordenio
 *
 */
@Mapper(componentModel = "spring")
public interface RefMusicMapper {

	RefMusicDto entitiyToDto(final RefMusic refMusic);

	RefMusic dtoToEntity(final RefMusicDto refMusicDto);

	List<RefMusicDto> entitiyToDto(final List<RefMusic> refMusics);

	List<RefMusic> dtoToEntity(final List<RefMusicDto> refMusicDtos);

}
