package com.rodion.telenor.domain;

public class ApiProduct extends ValueObject {
    private static final long serialVersionUID = 1L;

    private String city;
    private Double price;
    private String properties;
    private String type;

    private ApiProduct(Builder builder) {
        this.city = builder.city;
        this.price = builder.price;
        this.properties = builder.properties;
        this.type = builder.type;
    }

    public String getCity() {
        return city;
    }

    public Double getPrice() {
        return price;
    }

    public String getProperties() {
        return properties;
    }

    public String getType() {
        return type;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{city, price, properties, type};
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String city;
        private Double price;
        private String properties;
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

        public Builder withProperties(String properties) {
            this.properties = properties;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public ApiProduct build() {
            return new ApiProduct(this);
        }
    }
}
