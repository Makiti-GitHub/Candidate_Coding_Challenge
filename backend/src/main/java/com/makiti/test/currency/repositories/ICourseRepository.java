package com.makiti.test.currency.repositories;

import com.makiti.test.currency.models.Course;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author franck.keudem@gmail.com
 */

public interface ICourseRepository
    extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
  Optional<Course> findByIdAndPublished(Long id, boolean published);
}
