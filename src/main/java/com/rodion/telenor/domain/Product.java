package com.rodion.telenor.domain;

public class Product extends ValueObject {
    private static final long serialVersionUID = 1L;

    private String city;
    private Double price;
    private Property property;
    private String type;

    public Product() {
    }

    private Product(Builder builder) {
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

    public Property getProperty() {
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
        private Property property;
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

        public Builder withProperty(Property property) {
            this.property = property;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
