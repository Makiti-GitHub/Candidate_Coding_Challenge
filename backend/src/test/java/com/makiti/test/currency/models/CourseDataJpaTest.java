package com.makiti.test.currency.models;

import com.makiti.test.currency.dtos.CourseDto;
import com.makiti.test.currency.fakers.CourseFaker;
import com.makiti.test.currency.repositories.ICourseRepository;
import com.makiti.test.modules.exceptions.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author franck.keudem@gmail.com
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class CourseDataJpaTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ICourseRepository currencyRepository;

  @Test
  public void whenSaveCourse() {
    Course currency = CourseDto.getInstance(
      CourseFaker.getInitializedCourse()
    );
    
    Course currencySave = entityManager.persistAndFlush(currency);

    Course currencyFound = currencyRepository
      .findById(currencySave.getId())
      .orElseThrow(() ->
        new ResourceNotFoundException("Notation", "id", currencySave.getId())
      );

    Assertions.assertThat(currencyFound.getPrice()).isEqualTo(0.7F);

    Assertions.assertThat(currencyFound.getName()).isEqualTo("React JS");
    Assertions.assertThatObject(currencyFound.getDescription()).isEqualTo("Programmation en Live");
  }
}
