package com.makiti.test.modules.commons;

import java.util.Date;

public class RequestResult<T> {

  public Date timestamp;
  public T data;
  public boolean success;
  public String code;
  public Object details;

  public RequestResult() {}

  public RequestResult(Date timestamp, T data, boolean success, String code) {
    this.timestamp = timestamp;
    this.data = data;
    this.success = success;
    this.code = code;
  }

  public RequestResult(
    Date timestamp,
    T data,
    boolean success,
    String code,
    Object details
  ) {
    this.timestamp = timestamp;
    this.data = data;
    this.success = success;
    this.code = code;
    this.details = details;
  }

  public static <T> RequestResult<T> success(T data) {
    return new RequestResult<>(new Date(), data, true, "SUCCESS");
  }
  public static <T> RequestResult<T> error(T data) {
    return new RequestResult<>(new Date(), data, false, "FAIL");
  }
}
