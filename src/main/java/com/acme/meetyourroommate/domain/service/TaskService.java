package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    Page<Task> getAllTasks(Pageable pageable);
    Task getTaskById(Long taskId);

    Page<Task> getAllTasksByTeamId(Long teamId, Pageable pageable);
    Task getTaskByIdAndTeamId(Long teamId,Long taskId);

    Task createTask(Long teamId,Task task);
    Task updateTask(Long taskId, Task taskRequest);
    ResponseEntity<?> deleteTask(Long taskId);
}
