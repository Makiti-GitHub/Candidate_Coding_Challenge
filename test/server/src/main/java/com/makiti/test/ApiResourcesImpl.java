package com.makiti.test;

import com.makiti.test.boundary.TaskResource;
import com.makiti.test.boundary.TaskResourceImpl;

import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;

public class ApiResourcesImpl implements ApiResources {

    @Context
    ResourceContext context;

    @Override
    public TaskResource task() {
        return context.getResource(TaskResourceImpl.class);
    }

    @Override
    public String ping() {
        return "pong";
    }
}
