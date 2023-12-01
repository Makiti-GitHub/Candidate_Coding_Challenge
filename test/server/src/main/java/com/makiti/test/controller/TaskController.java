package com.makiti.test.controller;

import com.makiti.test.ErrorPayload;
import com.makiti.test.ProcessingException;
import com.makiti.test.entity.Task;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.*;
import java.util.List;

public class TaskController {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private NumberGenerator numberGenerator;

    public List<Task> getTasks() {
        TypedQuery<Task> query = em.createNamedQuery(Task.FIND_TASKS, Task.class);
        return query.getResultList();
    }

    public Task getTaskByKey(final String key) {
        TypedQuery<Task> query = em.createNamedQuery(Task.FIND_TASK_BY_KEY, Task.class)
                .setParameter(Task.KEY_ATTRIBUTE, key);
        return query.getSingleResult();
    }

    public Task saveTask(final String description) {
        final Task task = new Task();
        task.setKey("TASK-" + numberGenerator.getNextNumber());
        task.setDescription(description);

        try {
            userTransaction.begin();
            em.persist(task);
            userTransaction.commit();

        } catch (NotSupportedException | SystemException e) {
            final ErrorPayload errorPayload = new ErrorPayload();
            errorPayload.setErrorCode("TASK-0001");
            errorPayload.setDetails("error when creating task");
            errorPayload.setTitle("Task creation error");
            errorPayload.setConstraintViolation(e.getMessage());
            throw new ProcessingException(errorPayload);
        } catch (HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            final ErrorPayload errorPayload = new ErrorPayload();
            errorPayload.setErrorCode("TASK-0002");
            errorPayload.setDetails("error when saving task");
            errorPayload.setTitle("Task saving error");
            errorPayload.setConstraintViolation(e.getMessage());
            throw new ProcessingException(errorPayload);
        }

        return task;
    }
}
