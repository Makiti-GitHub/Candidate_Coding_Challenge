package com.makiti.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = Task.FIND_TASKS, query = "SELECT t FROM Task t"),
        @NamedQuery(name = Task.FIND_TASK_BY_KEY, query = "SELECT t FROM Task t WHERE t.key = :" + Task.KEY_ATTRIBUTE),
})
@Entity
public class Task {

    public static final String FIND_TASKS = "Task.findAll";
    public static final String FIND_TASK_BY_KEY = "Task.findByKey";

    public static final String KEY_ATTRIBUTE = "key";

    @Id
    @GeneratedValue
    private UUID id;

    @NotEmpty
    @Column(name = "taskKey")
    private String key;

    @NotEmpty
    private String description;

    @NotNull
    private ZonedDateTime created = ZonedDateTime.now(ZoneOffset.UTC);

    public TaskDTO toDTO() {
        final TaskDTO dto = new TaskDTO();
        dto.setKey(this.key);
        dto.setDescription(this.description);
        dto.setCreated(this.created);

        return dto;
    }

    public static List<TaskDTO> toDTOList(final List<Task> tasks) {
        final List<TaskDTO> dtoList = new ArrayList<>();
        for (final Task task : tasks) {
            dtoList.add(task.toDTO());
        }

        return dtoList;
    }
}
