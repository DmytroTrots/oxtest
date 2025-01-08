package com.trots.oxtest.mapper;

import com.trots.oxtest.dto.UserDTO;
import com.trots.oxtest.model.entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDTO dto);

    List<UserEntity> toEntities(List<UserDTO> dtos);

    UserDTO toDto(UserEntity entity);

    List<UserDTO> toDtos(List<UserEntity> entities);


}
