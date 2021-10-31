package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Task;
import com.acme.meetyourroommate.domain.service.TaskService;
import com.acme.meetyourroommate.resource.TaskResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TeamTasksController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TaskService taskService;

    @Operation(summary = "Get All Tasks by Team", description = "Get all tasks by Team", tags = {"tasks"})
    @GetMapping("/team/{teamId}/tasks")
    public Page<TaskResource> getAllTasksByTeamId(@PathVariable Long teamId, Pageable pageable){
        Page<Task> taskPage = taskService.getAllTasksByTeamId(teamId,pageable);

        List<TaskResource> resources = taskPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Task By Id And TeamId", description = "Get Task By Id And TeamId", tags = {"tasks"})
    @GetMapping("/team/{teamId}/tasks/{taskId}")
    public TaskResource getTaskByIdAndTeamId(@PathVariable Long teamId,@PathVariable Long taskId){
        return convertToResource(taskService.getTaskByIdAndTeamId(teamId,taskId));
    }
    private  TaskResource convertToResource(Task entity){return mapper.map(entity,TaskResource.class);}
}
