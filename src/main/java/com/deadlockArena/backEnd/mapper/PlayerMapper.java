package com.deadlockArena.backEnd.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockArena.backEnd.entity.Player;
import com.deadlockArena.dto.PlayerDto;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

	PlayerDto entitiyToDto(final Player Player);

	Player dtoToEntity(final PlayerDto Player);

	List<PlayerDto> entitiyToDto(final List<Player> Players);

	List<Player> dtoToEntity(final List<PlayerDto> PlayerDtos);

}
