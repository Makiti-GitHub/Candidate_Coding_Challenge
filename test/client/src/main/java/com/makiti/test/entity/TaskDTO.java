package com.makiti.test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {

    private String key;
    private String description;
    private ZonedDateTime created;
}
