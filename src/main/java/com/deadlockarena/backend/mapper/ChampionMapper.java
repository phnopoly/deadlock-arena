package com.deadlockarena.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockarena.backend.dto.ChampionDto;
import com.deadlockarena.backend.entity.Champion;

/**
 * Maps {@link Champion} to and from {@link ChampionDto}. Mapper Implementation
 * is generated on build.
 *
 * @author zsaordenio
 *
 */
@Mapper(componentModel = "spring")
public interface ChampionMapper {

	ChampionDto entitiyToDto(final Champion champion);

	Champion dtoToEntity(final ChampionDto championDto);

	List<ChampionDto> entitiyToDto(final List<Champion> champions);

	List<Champion> dtoToEntity(final List<ChampionDto> championDtos);

}
