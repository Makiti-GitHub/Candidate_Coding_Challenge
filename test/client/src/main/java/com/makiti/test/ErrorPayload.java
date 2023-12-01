package com.makiti.test;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.Response;
import java.io.Serializable;

@Getter
@Setter
public class ErrorPayload implements Serializable {
    private Response.Status status;
    private String errorCode;
    private String constraintViolation;
    private String title;
    private String details;
}
