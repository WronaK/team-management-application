package com.example.scrumer.task.web;

import com.example.scrumer.task.application.TasksService;
import com.example.scrumer.task.application.port.TasksUseCase;
import com.example.scrumer.task.application.port.TasksUseCase.CreateTaskCommand;
import com.example.scrumer.task.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TasksController {
    private final TasksService tasks;

    @GetMapping
    public List<Task> getAll(@AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        return tasks.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Task> getById(@PathVariable Long id,
                                  @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        return tasks.findById(id);
    }

    @PutMapping("/{id}/subtasks")
    public void addSubtask(@PathVariable Long id,
                           @RequestBody RestSubtaskCommand command,
                           @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        tasks.addSubtask(id, command.toCreateCommand());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id,
                           @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        tasks.deleteById(id);
    }

    @Data
    private static class RestSubtaskCommand {
        private String title;
        private String description;
        private Integer priority;

        CreateTaskCommand toCreateCommand() {
            return new CreateTaskCommand(title, description, priority);
        }
    }
}
