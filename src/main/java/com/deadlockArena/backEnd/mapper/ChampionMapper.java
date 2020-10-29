package com.deadlockArena.backEnd.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockArena.backEnd.entity.Champion;
import com.deadlockArena.dto.ChampionDto;

@Mapper(componentModel = "spring")
public interface ChampionMapper {

	ChampionDto entitiyToDto(final Champion champion);

	Champion dtoToEntity(final ChampionDto championDto);

	List<ChampionDto> entitiyToDto(final List<Champion> champions);

	List<Champion> dtoToEntity(final List<ChampionDto> championDtos);

}
