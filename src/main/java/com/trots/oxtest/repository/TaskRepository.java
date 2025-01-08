package com.trots.oxtest.repository;

import com.trots.oxtest.model.entity.ClientEntity;
import com.trots.oxtest.model.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
