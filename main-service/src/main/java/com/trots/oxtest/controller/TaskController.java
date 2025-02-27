package com.trots.oxtest.controller;

import com.trots.oxtest.dto.TaskDTO;
import com.trots.oxtest.service.TaskService;
import com.trots.oxtest.service.impl.TaskCreateManager;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v_0/task")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "task")
public class TaskController {

    private final TaskService taskService;
    private final TaskCreateManager taskCreateManager;

    @GetMapping
    @Cacheable(key = "'allTasks'")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TaskDTO> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public TaskDTO findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @GetMapping("/client/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TaskDTO> findAllByClientId(@PathVariable Long id) {
        return taskService.findAllByClientId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(key = "'allTasks'")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    @PostMapping
    @CacheEvict(key = "'allTasks'")
    @PreAuthorize("hasAuthority('ADMIN')")
    public TaskDTO save(@Valid @RequestBody TaskDTO taskDTO) {
        return taskCreateManager.createTask(taskDTO);
    }

    @PutMapping
    @CacheEvict(key = "'allTasks'")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public TaskDTO updateById(@Valid @RequestBody TaskDTO taskDTO) {
        return taskCreateManager.updateTask(taskDTO);
    }

}
