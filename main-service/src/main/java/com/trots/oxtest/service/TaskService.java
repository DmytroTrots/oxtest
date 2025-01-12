package com.trots.oxtest.service;

import com.trots.oxtest.dto.TaskDTO;
import com.trots.oxtest.model.entity.TaskEntity;
import java.util.List;

public interface TaskService {

    TaskDTO save(TaskEntity task);

    void deleteById(Long id);

    TaskDTO findById(Long id);

    List<TaskDTO> findAll();

    List<TaskDTO> findAllByClientId(Long id);

    TaskDTO update(TaskEntity task);
}
