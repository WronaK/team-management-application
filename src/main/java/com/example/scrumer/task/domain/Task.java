package com.example.scrumer.task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Integer priority;
    private Integer storyPoints;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private List<Subtask> subtasks;

    public Task(String title, String description, Integer priority, Integer storyPoints) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.storyPoints = storyPoints;
    }
}
