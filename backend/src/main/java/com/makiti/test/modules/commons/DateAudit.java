package com.makiti.test.modules.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author franck.keudem@gmail.com
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
  value = { "creationDate", "creationDate" },
  allowGetters = true
)
public abstract class DateAudit implements Serializable, Cloneable {

  @CreatedDate
  private Instant creationDate;

  @LastModifiedDate
  private Instant modificationDate;

  public Instant getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Instant creationDate) {
    this.creationDate = creationDate;
  }

  public Instant getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(Instant modificationDate) {
    this.modificationDate = modificationDate;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
