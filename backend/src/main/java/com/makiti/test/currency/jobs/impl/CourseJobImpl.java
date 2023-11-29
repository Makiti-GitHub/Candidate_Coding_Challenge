package com.makiti.test.currency.jobs.impl;

import com.makiti.test.currency.dtos.CourseDto;
import com.makiti.test.currency.jobs.CourseJob;
import com.makiti.test.currency.models.Course;
import com.makiti.test.currency.repositories.ICourseRepository;
import com.makiti.test.modules.exceptions.ResourceAlreadyExistException;
import com.makiti.test.modules.exceptions.ResourceNotFoundException;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author franck.keudem@gmail.com
 */

@Component
@AllArgsConstructor
public class CourseJobImpl implements CourseJob {

  private final ICourseRepository currencyRepository;

  @Transactional
  @Override
  public CourseDto save(CourseDto item) {
    Course itemSave = currencyRepository.save(CourseDto.getInstance(item));
    return CourseDto.getInstance(itemSave);
  }

  @Transactional
  @Override
  public CourseDto update(long id, CourseDto item) {
    Course itemOnDb = currencyRepository
        .findByIdAndPublished(id, true)
        .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

    itemOnDb.setName(item.name);
    itemOnDb.setDescription(item.description);
    itemOnDb.setPrice(item.price);

    return CourseDto.getInstance(currencyRepository.save(itemOnDb));
  }

  @Override
  public CourseDto get(long id) {
    Course item = currencyRepository
        .findByIdAndPublished(id, true)
        .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
    return CourseDto.getInstance(item);
  }

  @Override
  public void delete(long id) {
    Course itemToDelete = currencyRepository
        .findByIdAndPublished(id, true)
        .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
    itemToDelete.prepareToDelete();
    currencyRepository.save(itemToDelete);
  }

  @Override
  public Page<CourseDto> getAll(Pageable pageable) {
    return currencyRepository.findAll(pageable)
        .map(CourseDto::getInstance);
  }
}
