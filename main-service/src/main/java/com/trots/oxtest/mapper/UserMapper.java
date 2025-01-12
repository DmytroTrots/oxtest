package com.trots.oxtest.mapper;

import com.trots.oxtest.dto.UserDTO;
import com.trots.oxtest.model.Role;
import com.trots.oxtest.model.RoleAuth;
import com.trots.oxtest.model.entity.UserEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToEntities")
    UserEntity toEntity(UserDTO dto);

    List<UserEntity> toEntities(List<UserDTO> dtos);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToDto")
    UserDTO toDto(UserEntity entity);

    List<UserDTO> toDtos(List<UserEntity> entities);

    @Named("mapRolesToEntities")
    default List<Role> mapRolesToEntities(List<String> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(RoleAuth.valueOf(roleName));
                    return role;
                })
                .collect(Collectors.toList());
    }

    @Named("mapRolesToDto")
    default List<String> mapRolesToDto(List<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());
    }

}
