package com.makiti.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = NumberStore.FIND_NUMBER_STORE, query = "SELECT ns FROM NumberStore ns")
})
@Entity
public class NumberStore {

    public static final String FIND_NUMBER_STORE = "NumberStore.findAll";

    @Id
    @GeneratedValue
    private UUID id;

    private long lastNumber;
}
