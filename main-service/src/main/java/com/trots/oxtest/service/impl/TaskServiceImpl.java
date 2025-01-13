package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.TaskDTO;
import com.trots.oxtest.exception.ResourceNotFoundException;
import com.trots.oxtest.mapper.TaskMapper;
import com.trots.oxtest.model.TaskStatus;
import com.trots.oxtest.model.entity.TaskEntity;
import com.trots.oxtest.repository.TaskRepository;
import com.trots.oxtest.service.TaskService;
import com.trots.proxy.NotificationProxy;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final NotificationProxy notificationProxy;

    @Override
    public TaskDTO save(TaskEntity task) {
        task.setStatus(TaskStatus.OPEN);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO findById(Long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException(TaskEntity.class, id));
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskMapper.toDtos(taskRepository.findAll());
    }

    @Override
    public List<TaskDTO> findAllByClientId(Long id) {
        return taskMapper.toDtos(taskRepository.findAllByContactClientId(id));
    }

    @Override
    public TaskDTO update(TaskEntity task) {
        TaskEntity existingTask = taskRepository.findById(task.getId()).orElseThrow(() ->
               new ResourceNotFoundException(TaskEntity.class, task.getId()));

        TaskEntity oldTask = new TaskEntity(existingTask.getDescription(),
                                              existingTask.getStatus(),
                                              existingTask.getDeadlineTime(),
                                              existingTask.getContact());

        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setDeadlineTime(task.getDeadlineTime());
        existingTask.setContact(task.getContact());

        TaskDTO updatedTask = taskMapper.toDto(taskRepository.save(existingTask));

        sendTaskNotification(oldTask, updatedTask);

        return updatedTask;
    }

    private void sendTaskNotification(TaskEntity existingTask, TaskDTO taskDTO) {
        if (!existingTask.getStatus().equals(taskDTO.getStatus()) && existingTask.getContact() != null) {
            notificationProxy
                    .sendNotificationToUser( "Task status Is changed from " + existingTask.getStatus().name() + " to " + taskDTO.getStatus(),
                                             existingTask.getContact().getUser().getId().toString());
        }

        if (!(existingTask.getDeadlineTime().getTime() == taskDTO.getDeadlineTime().getTime()) && existingTask.getContact() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            notificationProxy
                    .sendNotificationToUser("Task deadline Is changed from " + formatter.format(existingTask.getDeadlineTime())
                                                    + " to " + formatter.format(taskDTO.getDeadlineTime()) ,
                                            existingTask.getContact().getUser().getId().toString());
        }
    }

}
