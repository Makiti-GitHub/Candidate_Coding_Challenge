package com.makiti.test;

import javax.ejb.ApplicationException;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationException(rollback = true)
public class ProcessingException extends ResponseProcessingException {
    public static final String HEADER_RESPONSE_TYPE ="X-Response-Type";
    public static final String HEADER_COUNT ="X-Count";
    final transient Set<ErrorPayload> errors;

    public ProcessingException(ErrorPayload error) {
        this((Collection) Arrays.asList(error));
    }

    public ProcessingException(ErrorPayload error, Throwable cause) {
        this((Collection) Arrays.asList(error), cause);
    }

    public ProcessingException(ErrorPayload... errors) {
        this((Collection) Arrays.asList(errors));
    }

    public ProcessingException(Collection<ErrorPayload> errors) {
        super(Response.status(Response.Status.BAD_REQUEST).build(),
                errors.stream().map(ErrorPayload::getTitle).collect(Collectors.joining(", ")));
        this.errors = new LinkedHashSet<>();
        this.errors.addAll(errors);
    }

    public ProcessingException(Collection<ErrorPayload> errors, Throwable cause) {
        super(Response.status(Response.Status.BAD_REQUEST).build(),
                errors.stream().map(ErrorPayload::getTitle).collect(Collectors.joining(", ")), cause);
        this.errors = new LinkedHashSet<>();
        this.errors.addAll(errors);
    }

    public String getMessage() {
        return this.errors.toString();
    }
}
