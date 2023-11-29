package com.makiti.test.currency.dtos;

import com.makiti.test.currency.models.Course;

/**
 * @author franck.keudem@gmail.com
 */

public class CourseDto {

  public Long id;
  public String name;
  public Float price;
  public String description;

  public static CourseDto getInstance(Course item) {
    CourseDto itemDto = new CourseDto();
    itemDto.id = item.getId();
    itemDto.name = item.getName();
    itemDto.price = item.getPrice();
    itemDto.description = item.getDescription();

    return itemDto;
  }

  public static Course getInstance(CourseDto itemDto) {
    Course item = new Course();
    item.setId(itemDto.id);
    item.setName(itemDto.name);
    item.setPrice(itemDto.price);
    item.setDescription(itemDto.description);

    return item;
  }
}
