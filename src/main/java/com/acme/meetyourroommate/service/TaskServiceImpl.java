package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Task;
import com.acme.meetyourroommate.domain.repository.TaskRepository;
import com.acme.meetyourroommate.domain.repository.TeamRepository;
import com.acme.meetyourroommate.domain.service.TaskService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task","Id",taskId));
    }

    @Override
    public Page<Task> getAllTasksByTeamId(Long teamId, Pageable pageable) {
        return taskRepository.findByTeamId(teamId,pageable);
    }

    @Override
    public Task getTaskByIdAndTeamId(Long teamId, Long taskId) {
        return taskRepository.findByIdAndTeamId(teamId,taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Team or task not found"));
    }

    @Override
    public Task createTask(Long teamId, Task task) {
        return teamRepository.findById(teamId).map(team ->{
            task.setTeam(team);
            return taskRepository.save(task);
        }).orElseThrow(()->new ResourceNotFoundException("Team","Id",teamId));
    }

    @Override
    public Task updateTask(Long taskId, Task taskRequest) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task","Id",taskId));
        task.setActive(taskRequest.getActive());
        task.setDescription(taskRequest.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public ResponseEntity<?> deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task","Id",taskId));
        taskRepository.delete(task);
        return ResponseEntity.ok().build();
    }
}
