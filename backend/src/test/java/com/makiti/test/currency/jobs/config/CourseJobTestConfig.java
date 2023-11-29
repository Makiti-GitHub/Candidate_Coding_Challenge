package com.makiti.test.currency.jobs.config;

import com.makiti.test.currency.jobs.CourseJob;
import com.makiti.test.currency.jobs.impl.CourseJobImpl;
import com.makiti.test.currency.repositories.ICourseRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CourseJobTestConfig {
  @Bean
  public ICourseRepository currencyRepository() {
    return Mockito.mock(ICourseRepository.class);
  }

  @Bean
  public CourseJob currencyJob() {
    return new CourseJobImpl(currencyRepository());
  }
}
