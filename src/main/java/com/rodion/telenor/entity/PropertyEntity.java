package com.rodion.telenor.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity(name = "Property")
public class PropertyEntity extends AbstractEntity {
    private String color;
    private Integer gb_limit;

    @OneToOne(mappedBy = "property")
    private ProductEntity product;

    public PropertyEntity() {
    }

    private PropertyEntity(Builder builder) {
        this.color = builder.color;
        this.gb_limit = builder.gb_limit;
    }

    public String getColor() {
        return color;
    }

    public Integer getGb_limit() {
        return gb_limit;
    }

    public ProductEntity getProduct() {
        return product;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{color, gb_limit};
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String color;
        private Integer gb_limit;

        private Builder() {
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withGbLimit(Integer gb_limit) {
            this.gb_limit = gb_limit;
            return this;
        }

        public PropertyEntity build() {
            return new PropertyEntity(this);
        }
    }
}
