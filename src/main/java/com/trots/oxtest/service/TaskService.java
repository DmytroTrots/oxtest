package com.trots.oxtest.service;

import com.trots.oxtest.dto.TaskDTO;
import java.util.List;

public interface TaskService {

    TaskDTO save(TaskDTO taskDto);

    void deleteById(Long id);

    TaskDTO findById(Long id);

    List<TaskDTO> findAll();

    TaskDTO updateById(Long id, TaskDTO taskDTO);
}
