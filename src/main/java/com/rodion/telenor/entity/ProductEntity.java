package com.rodion.telenor.entity;

import com.rodion.telenor.domain.ParameterConstraints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

@Entity(name = "Product")
public class ProductEntity extends AbstractEntity {
    private String city;
    private Double price;

    @Pattern(regexp = ParameterConstraints.REGEXP_PHONE_OR_SUBSCRIPTION, message = "wrong parameters name")
    private String type;

    @OneToOne(cascade = CascadeType.PERSIST)
    private PropertyEntity property;

    public ProductEntity() {
    }

    private ProductEntity(Builder builder) {
        this.city = builder.city;
        this.price = builder.price;
        this.property = builder.property;
        this.type = builder.type;
    }

    public String getCity() {
        return city;
    }

    public Double getPrice() {
        return price;
    }

    public PropertyEntity getProperty() {
        return property;
    }

    public String getType() {
        return type;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{city, price, property, type};
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String city;
        private Double price;
        private PropertyEntity property;
        private String type;

        private Builder() {
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder withProperty(PropertyEntity property) {
            this.property = property;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public ProductEntity build() {
            return new ProductEntity(this);
        }
    }
}
