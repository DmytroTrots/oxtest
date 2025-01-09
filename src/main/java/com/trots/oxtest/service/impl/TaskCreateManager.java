package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.ContactDTO;
import com.trots.oxtest.dto.TaskDTO;
import com.trots.oxtest.mapper.ContactMapper;
import com.trots.oxtest.mapper.TaskMapper;
import com.trots.oxtest.model.entity.TaskEntity;
import com.trots.oxtest.service.ContactService;
import com.trots.oxtest.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskCreateManager {

    private final ContactService contactService;
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final ContactMapper contactMapper;

    public TaskDTO createTask(TaskDTO taskDTO) {
        ContactDTO existingContact = contactService.findById(taskDTO.getContactId());
        TaskEntity taskToSave = taskMapper.toEntity(taskDTO);
        taskToSave.setContact(contactMapper.toEntity(existingContact));
        return taskService.save(taskToSave);
    }

    @Transactional
    public TaskDTO updateTask(TaskDTO taskDTO) {
        ContactDTO existingContact = contactService.findById(taskDTO.getContactId());
        TaskEntity taskToSave = taskMapper.toEntity(taskDTO);

        taskToSave.setContact(contactMapper.toEntity(existingContact));

        return taskService.update(taskToSave);
    }

}
