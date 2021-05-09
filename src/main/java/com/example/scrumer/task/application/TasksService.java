package com.example.scrumer.task.application;

import com.example.scrumer.task.application.port.TasksUseCase;
import com.example.scrumer.task.db.SubtaskJpaRepository;
import com.example.scrumer.task.db.TaskDetailsJpaRepository;
import com.example.scrumer.task.db.TaskJpaRepository;
import com.example.scrumer.task.domain.Subtask;
import com.example.scrumer.task.domain.Task;
import com.example.scrumer.task.domain.TaskDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TasksService implements TasksUseCase {
    private final TaskJpaRepository repository;
    private final SubtaskJpaRepository subtasksRepository;
    private final TaskDetailsJpaRepository taskDetailsRepository;

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void addSubtask(Long id, CreateTaskCommand command) {
        repository.findById(id)
                .ifPresent(task -> {
                    TaskDetails taskDetails = taskDetailsRepository
                            .save(TaskDetails.builder()
                                    .title(command.getTitle())
                                    .description( command.getDescription())
                                    .priority(command.getPriority())
                                    .build());
                    Subtask subtask = subtasksRepository
                            .save(Subtask.builder()
                                    .taskDetails(taskDetails)
                                    .build());
                    task.addSubtask(subtask);
                    repository.save(task);
                });

    }
}

