package com.makiti.test.currency.models;

import com.makiti.test.modules.commons.UserDateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author franck.keudem@gmail.com
 */

@Entity
@Table(name = "course_table")
@NoArgsConstructor
@Getter
@Setter
public class Course extends UserDateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  Long id;

  String name;
  Float price;
  String description;

  boolean published = true;


  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj == this)
      return true;
    return Objects.equals(this.getId(), ((Course) obj).getId());
  }

  public void prepareToDelete() {
    this.published = false;
  }
}
