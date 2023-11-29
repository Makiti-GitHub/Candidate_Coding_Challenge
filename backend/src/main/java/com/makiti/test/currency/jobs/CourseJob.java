package com.makiti.test.currency.jobs;

import com.makiti.test.currency.dtos.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CourseJob {
  public CourseDto save(CourseDto item);

  public CourseDto update(long id, CourseDto item);

  public CourseDto get(long id);

  public void delete(long id);

  public Page<CourseDto> getAll(Pageable pageable);
}
