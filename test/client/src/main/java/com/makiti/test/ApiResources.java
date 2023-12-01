package com.makiti.test;

import com.makiti.test.boundary.TaskResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("default")
public interface ApiResources {

    @Path("tasks")
    TaskResource task();

    @GET
    @Path("ping")
    String ping();
}
