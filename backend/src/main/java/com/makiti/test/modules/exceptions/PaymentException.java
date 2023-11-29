package com.makiti.test.modules.exceptions;

/**
 * @author "KEUDEM Franck"
 * @creaed 27/05/2022
 */
public class PaymentException extends RuntimeException {

  public PaymentException(String message) {
    super(message);
  }

  public PaymentException(String message, Throwable cause) {
    super(message, cause);
  }
}

