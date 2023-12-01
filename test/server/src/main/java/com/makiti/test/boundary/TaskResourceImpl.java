package com.makiti.test.boundary;

import com.makiti.test.controller.TaskController;
import com.makiti.test.entity.Task;
import com.makiti.test.entity.TaskDTO;

import javax.inject.Inject;
import java.util.List;

public class TaskResourceImpl implements TaskResource {

    @Inject
    private TaskController taskController;

    @Override
    public List<TaskDTO> getTasks() {
        final List<Task> tasks = taskController.getTasks();
        return Task.toDTOList(tasks);
    }

    @Override
    public TaskDTO getTask(final String key) {
        return taskController.getTaskByKey(key).toDTO();
    }

    @Override
    public TaskDTO createTask(final String description) {
        return taskController.saveTask(description).toDTO();
    }
}
