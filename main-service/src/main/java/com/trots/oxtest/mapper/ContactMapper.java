package com.trots.oxtest.mapper;

import com.trots.oxtest.dto.ContactDTO;
import com.trots.oxtest.model.entity.ContactEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface ContactMapper {

    ContactEntity toEntity(ContactDTO dto);

    List<ContactEntity> toEntities(List<ContactDTO> dtos);

    @Mapping(target = "clientId", source = "client.id")
    ContactDTO toDto(ContactEntity entity);

    List<ContactDTO> toDtos(List<ContactEntity> entities);


}
