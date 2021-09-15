package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Team;
import com.acme.meetyourroommate.domain.service.TeamService;
import com.acme.meetyourroommate.resource.TeamResource;
import com.acme.meetyourroommate.resource.save.SaveTeamResource;
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
public class TeamController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Get All Teams", description = "Get all teams", tags = {"teams"})
    @GetMapping("/teams")
    public Page<TeamResource> getAllTeams(Pageable pageable){
        Page<Team> teamPage = teamService.getAllTeams(pageable);

        List<TeamResource> resources = teamPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Team By Id", description = "Get Team By Id", tags = {"teams"})
    @GetMapping("/teams/{teamId}")
    public TeamResource getTeamById(@PathVariable Long teamId){
        return convertToResource(teamService.getTeamById(teamId));
    }

    @Operation(summary = "Create Team", description = "Create a new team", tags = {"teams"})
    @PostMapping("/teams")
    public TeamResource createTeam(@Valid @RequestBody SaveTeamResource resource){
        Team team = convertToEntity(resource);
        return convertToResource(teamService.createTeam(team));
    }

    @Operation(summary = "Update Team", description = "Update a team", tags = {"teams"})
    @PutMapping("/teams/{teamId}")
    public TeamResource updateTeam(
            @PathVariable Long teamId,
            @RequestBody @Valid SaveTeamResource resource){
        Team team = convertToEntity(resource);
        return convertToResource(teamService.updateTeam(team,teamId));
    }

    @Operation(summary = "Delete a team", description = "Delete a Team", tags = {"teams"})
    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long teamId){
        return teamService.deleteTeam(teamId);
    }

    private  Team convertToEntity(SaveTeamResource resource){return mapper.map(resource,Team.class);}
    private  TeamResource convertToResource(Team entity){return mapper.map(entity,TeamResource.class);}
}
