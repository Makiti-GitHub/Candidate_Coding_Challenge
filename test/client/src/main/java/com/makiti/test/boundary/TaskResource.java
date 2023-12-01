package com.makiti.test.boundary;

import com.makiti.test.entity.TaskDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
public interface TaskResource {

    @GET
    List<TaskDTO> getTasks();

    @GET
    @Path("/{id}")
    TaskDTO getTask(@PathParam("id") final String key);

    @POST
    TaskDTO createTask(final String description);
}
