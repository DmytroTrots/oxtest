package com.trots.oxtest.mapper;

import com.trots.oxtest.dto.TaskDTO;
import com.trots.oxtest.model.entity.TaskEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskEntity toEntity(TaskDTO dto);

    List<TaskEntity> toEntities(List<TaskDTO> dtos);

    @Mapping(target = "contactId", source = "contact.id")
    TaskDTO toDto(TaskEntity entity);

    List<TaskDTO> toDtos(List<TaskEntity> entities);

}
