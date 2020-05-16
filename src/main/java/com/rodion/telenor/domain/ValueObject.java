package com.rodion.telenor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class ValueObject implements Serializable {
    public ValueObject() {
    }

    @JsonIgnore
    protected abstract Object[] getIdFields();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj != null && this.getClass().equals(obj.getClass()) ? (new EqualsBuilder()).append(this.getIdFields(), ((ValueObject) obj).getIdFields()).isEquals() : false;
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder(17, 37)).append(this.getIdFields()).toHashCode();
    }

    public String toString() {
        return (String) Arrays.stream(this.getIdFields()).map(String::valueOf).collect(Collectors.joining(","));
    }

    protected static Object[] append(Object[] original, Object[] appended) {
        Object[] result = Arrays.copyOf(original, original.length + appended.length);
        System.arraycopy(appended, 0, result, original.length, appended.length);
        return result;
    }
}
