package com.makiti.test.currency.fakers;

import com.makiti.test.currency.dtos.CourseDto;
import com.makiti.test.modules.faker.FakerUtils;

/**
 * @author franck.keudem@gmail.com
 */

public class CourseFaker {

  public static CourseDto getInitializedCourse() {
    CourseDto itemDto = new CourseDto();

    itemDto.price = 0.7F;
    itemDto.name = "React JS";
    itemDto.description = "Programmation en Live ";

    return itemDto;
  }

  public static CourseDto getRandomCourse() {
    CourseDto itemDto = new CourseDto();
    itemDto.price =
        Float.parseFloat(FakerUtils.fakeValuesService.numerify("#####.##"));
    itemDto.name =
        FakerUtils.fakeValuesService.letterify("????????????????????????");
    itemDto.description =
        FakerUtils.fakeValuesService.letterify("????????????????????????");

    return itemDto;
  }
}
