package com.rodion.telenor.domain;

public class Product extends ValueObject {
    private static final long serialVersionUID = 1L;

    private String type;
    private Double price;
    private String city;
    private Property property;

    public Product() {
    }

    private Product(Builder builder) {
        this.city = builder.city;
        this.price = builder.price;
        this.type = builder.type;
        this.property = builder.property;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public String getCity() {
        return city;
    }

    public Property getProperty() {
        return property;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{city, price, property, type};
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private Double price;
        private String city;
        private Property property;

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

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withProperty(Property property) {
            this.property = property;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
