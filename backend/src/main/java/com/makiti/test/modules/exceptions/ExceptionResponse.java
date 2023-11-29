package com.makiti.test.modules.exceptions;

import java.util.Date;

public class ExceptionResponse {
  private static class Data {
    public String message;
    public String details;
    public String httpCodeMessage;
  }

  private final Date timestamp;
  private final Data data;

  public ExceptionResponse(
    Date timestamp,
    String message,
    String details,
    String httpCodeMessage
  ) {
    super();
    this.timestamp = timestamp;
    this.data = new Data();
    this.data.message = message;
    this.data.details = details;
    this.data.httpCodeMessage = httpCodeMessage;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public boolean isSuccess() {
    return false;
  }

  public String getCode() {
    return "FAIL";
  }

  public Data getData() {
    return data;
  }
}
