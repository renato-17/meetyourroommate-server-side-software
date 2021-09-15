package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Task;
import com.acme.meetyourroommate.domain.service.TaskService;
import com.acme.meetyourroommate.resource.TaskResource;
import com.acme.meetyourroommate.resource.save.SaveTaskResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Get All Tasks", description = "Get all tasks", tags = {"tasks"})
    @GetMapping("/tasks")
    public Page<TaskResource> getAllTasks(Pageable pageable){
        Page<Task> taskPage = taskService.getAllTasks(pageable);

        List<TaskResource> resources = taskPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Task By Id", description = "Get Task By Id", tags = {"tasks"})
    @GetMapping("/tasks/{taskId}")
    public TaskResource getTaskById(@PathVariable Long taskId){
        return convertToResource(taskService.getTaskById(taskId));
    }

    @Operation(summary = "Create Task", description = "Create a new Task", tags = {"tasks"})
    @PostMapping("/tasks")
    public TaskResource createTask(@Valid @RequestBody SaveTaskResource resource){
        Task task = convertToEntity(resource);
        return convertToResource(taskService.createTask(resource.getTeamId(),task));
    }

    @Operation(summary = "Update Task", description = "Update Task", tags = {"tasks"})
    @PutMapping("/tasks/{taskId}")
    public TaskResource updateTask(
            @PathVariable Long taskId,
            @RequestBody @Valid SaveTaskResource resource){
        Task task = convertToEntity(resource);
        return convertToResource(taskService.updateTask(taskId, task));
    }

    @Operation(summary = "Delete Task", description = "Delete Task", tags = {"tasks"})
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }

    private  Task convertToEntity(SaveTaskResource resource){return mapper.map(resource,Task.class);}
    private  TaskResource convertToResource(Task entity){return mapper.map(entity,TaskResource.class);}

}
