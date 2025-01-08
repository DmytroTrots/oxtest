package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.TaskDTO;
import com.trots.oxtest.mapper.TaskMapper;
import com.trots.oxtest.model.entity.TaskEntity;
import com.trots.oxtest.repository.TaskRepository;
import com.trots.oxtest.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDTO save(TaskDTO taskDto) {
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskDto)));
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO findById(Long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Task not found", id));
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskMapper.toDtos(taskRepository.findAll());
    }

    @Override
    public TaskDTO updateById(Long id, TaskDTO taskDTO) {
        TaskEntity existingTask = taskRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Task not found for update", id));

        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setDeadlineTime(taskDTO.getDeadlineTime());
        return taskMapper.toDto(existingTask);
    }

}
