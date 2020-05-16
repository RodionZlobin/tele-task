package com.rodion.telenor.entity;

import com.rodion.telenor.domain.ValueObject;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity extends ValueObject {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    protected AbstractEntity() {
    }

    public Long getId() {
        return id;
    }
}
