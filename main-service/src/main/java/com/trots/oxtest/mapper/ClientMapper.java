package com.trots.oxtest.mapper;

import com.trots.oxtest.dto.ClientDTO;
import com.trots.oxtest.model.entity.ClientEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ContactMapper.class)
public interface ClientMapper {

    ClientEntity toEntity(ClientDTO dto);

    List<ClientEntity> toEntities(List<ClientDTO> dtos);

    ClientDTO toDto(ClientEntity entity);

    List<ClientDTO> toDtos(List<ClientEntity> entities);


}
