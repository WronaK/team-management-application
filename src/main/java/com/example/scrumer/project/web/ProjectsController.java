package com.example.scrumer.project.web;

import com.example.scrumer.project.application.ProjectsService;
import com.example.scrumer.project.application.port.ProjectsUseCase.CreateProjectCommand;
import com.example.scrumer.project.application.port.ProjectsUseCase.TeamCommand;
import com.example.scrumer.project.converter.ProjectToProjectRequestConverter;
import com.example.scrumer.project.domain.Project;

import com.example.scrumer.project.request.ProjectRequest;
import com.example.scrumer.project.request.UpdateProjectRequest;
import com.example.scrumer.task.application.port.TasksUseCase.CreateTaskCommand;
import com.example.scrumer.task.converter.TaskToTaskRequestConverter;
import com.example.scrumer.task.request.TaskRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectsController {
    private final ProjectsService projects;
    private final ProjectToProjectRequestConverter projectConverter;
    private final TaskToTaskRequestConverter taskConverter;

    @GetMapping
    public List<ProjectRequest> getAll() {
        return projects.findAll().stream()
                .map(projectConverter::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProjectRequest getById(@PathVariable Long id) {
        Optional<Project> project = projects.findById(id);
        return project.map(projectConverter::toDto).orElseThrow(() -> new IllegalArgumentException("Not found project id: " + id));
    }

    @GetMapping("/{id}/update")
    public UpdateProjectRequest getProjectById(@PathVariable Long id) {
        Optional<Project> project = projects.findById(id);
        return project.map(projectConverter::toDtoUpdate).orElseThrow(() -> new IllegalArgumentException("Not found project id: " + id));
    }

    @GetMapping("/{id}/product_backlog")
    public List<TaskRequest> getProductBacklogById(@PathVariable Long id) {
        return projects.getProductBacklog(id).stream()
                .map(taskConverter::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addProject(@RequestBody RestCreateProjectCommand command) {
        Project project = projects.addProject(command.toCreateCommand(), getUserEmail());
        return ResponseEntity.created(createdProjectUri(project)).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProject(@RequestBody UpdateProjectRequest project) {
        projects.updateProject(project);
    }

    @PutMapping("/{id}/product_backlog")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTaskToProductBacklog(@PathVariable Long id,
                                        @RequestBody RestCreateTaskCommand command) {
        projects.addTaskToProductBacklog(id, command.toCreateCommand());
    }

    @PutMapping("/{id}/teams")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addTeamToProject(@PathVariable Long id, @RequestBody RestTeamsCommand command) {
        projects.addTeamToProject(id, command.toCommands());
    }

    @PatchMapping("/{id}/teams/{idTeam}/remove")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeTeamWithProject(@PathVariable Long id, @PathVariable Long idTeam) {
        projects.removeTeamWithProject(id, idTeam);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        projects.removeById(id);
    }

    private String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
    private URI createdProjectUri(Project project) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + project.getId().toString()).build().toUri();
    }

    @Data
    private static class RestCreateProjectCommand {
        private String name;
        private String accessCode;
        private String description;
        private String productOwner;
        private String scrumMaster;
        private Set<RestTeamCommand> teams;

        CreateProjectCommand toCreateCommand() {
            return new CreateProjectCommand(name, accessCode, description, productOwner, scrumMaster, toCommands(teams));
        }

        Set<TeamCommand> toCommands(Set<RestTeamCommand> teams) {
            Set<TeamCommand> listTeam = new HashSet<>();
            for (RestTeamCommand team: teams) {
                listTeam.add(team.toCommand());
            }
            return listTeam;
        }
    }

    @Data
    private static class RestCreateTaskCommand {
        private String title;
        private String description;
        private Integer priority;

        CreateTaskCommand toCreateCommand() {
            return new CreateTaskCommand(title, description, priority);
        }
    }

    @Data
    private static class RestTeamsCommand {
        private Set<RestTeamCommand> teams;

        Set<TeamCommand> toCommands() {
            Set<TeamCommand> listTeam = new HashSet<>();
            for (RestTeamCommand team: this.teams) {
                listTeam.add(team.toCommand());
            }
            return listTeam;
        }
    }

    @Data
    private static class RestTeamCommand {
        private String name;
        private String accessCode;

        TeamCommand toCommand() {
            return new TeamCommand(name, accessCode);
        }

    }
}
