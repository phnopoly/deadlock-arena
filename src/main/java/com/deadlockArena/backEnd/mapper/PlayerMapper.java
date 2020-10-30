package com.deadlockArena.backEnd.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deadlockArena.backEnd.dto.PlayerDto;
import com.deadlockArena.backEnd.entity.Player;

/**
 * Maps {@link Player} to and from {@link PlayerDto}. Mapper Implementation is
 * generated on build.
 *
 * @author zsaordenio
 *
 */
@Mapper(componentModel = "spring")
public interface PlayerMapper {

	PlayerDto entitiyToDto(final Player Player);

	Player dtoToEntity(final PlayerDto Player);

	List<PlayerDto> entitiyToDto(final List<Player> Players);

	List<Player> dtoToEntity(final List<PlayerDto> PlayerDtos);

}
