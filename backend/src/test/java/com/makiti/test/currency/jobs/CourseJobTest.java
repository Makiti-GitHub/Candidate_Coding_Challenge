package com.makiti.test.currency.jobs;

import com.makiti.test.currency.dtos.CourseDto;
import com.makiti.test.currency.fakers.CourseFaker;
import com.makiti.test.currency.jobs.config.CourseJobTestConfig;
import com.makiti.test.currency.models.Course;
import com.makiti.test.currency.repositories.ICourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(CourseJobTestConfig.class)
public class CourseJobTest {
  @Autowired
  CourseJob courseJob;

  @Autowired
  ICourseRepository courseRepository;

  @BeforeEach
  public void restoreMock() {
    Mockito.reset(courseRepository);
  }

  @Test
  public void when_create_course_successfully() {
    CourseDto courseDto = CourseFaker.getInitializedCourse();
    courseDto.id = 5L;

    Course course = CourseDto.getInstance(courseDto);

    Mockito.when(courseRepository.save(course)).thenReturn(course);
    CourseDto courseDto1 = courseJob.save(courseDto);

    Assertions.assertEquals(5L, courseDto1.id);
  }
}
