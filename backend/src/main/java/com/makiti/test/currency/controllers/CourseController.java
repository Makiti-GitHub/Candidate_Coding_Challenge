package com.makiti.test.currency.controllers;

import com.makiti.test.currency.dtos.CourseDto;
import com.makiti.test.modules.commons.RequestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "System configuration", value = "API to manage course")
@RestController
@RequestMapping("/api/course")
public interface CourseController {
  @PostMapping("/")
  @ApiOperation(value = "Create new course")
  public RequestResult<CourseDto> save(@Valid @RequestBody CourseDto item);

  @GetMapping("/{id}")
  @ApiOperation(value = "Get a specific course")
  public RequestResult<CourseDto> get(@PathVariable(value = "id") long id);

  @PutMapping("/{id}")
  @ApiOperation(value = "Update a course")
  public RequestResult<CourseDto> update(
      @PathVariable(value = "id") long id,
      @Valid @RequestBody CourseDto item
  );

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete a course")
  public RequestResult<Boolean> delete(@PathVariable(value = "id") long id);

  @GetMapping("/")
  @ApiOperation(value = "Get all currencies")
  public RequestResult<Page<CourseDto>> getAll(Pageable pgble);
}
