/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makiti.test.modules.exceptions;

/**
 *
 * @author franck.keudem@gmail.com
 */
public class ResourceAlreadyExistException extends RuntimeException {

  private final String resourceName;
  private final String fieldName;
  private final Object fieldValue;

  public ResourceAlreadyExistException(
    String resourceName,
    String fieldName,
    Object fieldValue
  ) {
    super(
      String.format(
        "%s already exist with %s : '%s'",
        resourceName,
        fieldName,
        fieldValue
      )
    );
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

  public String getResourceName() {
    return resourceName;
  }

  public String getFieldName() {
    return fieldName;
  }

  public Object getFieldValue() {
    return fieldValue;
  }
}
