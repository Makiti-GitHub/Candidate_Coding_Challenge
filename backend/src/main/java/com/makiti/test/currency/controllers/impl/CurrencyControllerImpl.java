package com.makiti.test.currency.controllers.impl;

import com.makiti.test.currency.controllers.CourseController;
import com.makiti.test.currency.dtos.CourseDto;
import com.makiti.test.currency.jobs.CourseJob;
import com.makiti.test.modules.commons.RequestResult;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author franck.keudem@gmail.com
 */

@Component
@AllArgsConstructor
public class CurrencyControllerImpl implements CourseController {
  private CourseJob currencyJob;

  @Override
  public RequestResult<CourseDto> save(@Valid @RequestBody CourseDto item) {
    return RequestResult.success(currencyJob.save(item));
  }

  @Override
  public RequestResult<CourseDto> get(@PathVariable(value = "id") long id) {
    return RequestResult.success(currencyJob.get(id));
  }

  @Override
  public RequestResult<CourseDto> update(
      @PathVariable(value = "id") long id,
      @Valid @RequestBody CourseDto item
  ) {
    return RequestResult.success(currencyJob.update(id, item));
  }

  @Override
  public RequestResult<Boolean> delete(@PathVariable(value = "id") long id) {
    currencyJob.delete(id);
    return RequestResult.success(true);
  }

  @Override
  public RequestResult<Page<CourseDto>> getAll(Pageable pgble) {
    return RequestResult.success(currencyJob.getAll(pgble));
  }
}
