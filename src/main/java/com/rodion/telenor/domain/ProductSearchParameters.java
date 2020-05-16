package com.rodion.telenor.domain;

public class ProductSearchParameters {
    private String type;
    private Double minPrice;
    private Double maxPrice;
    private String city;
    private String property;
    private String propertyColor;
    private Integer propertyGbLimitMax;
    private Integer propertyGbLimitMin;

    private ProductSearchParameters(Builder builder) {
        this.type = builder.type;
        this.minPrice = builder.minPrice;
        this.maxPrice = builder.maxPrice;
        this.city = builder.city;
        this.property = builder.property;
        this.propertyColor = builder.propertyColor;
        this.propertyGbLimitMax = builder.propertyGbLimitMax;
        this.propertyGbLimitMin = builder.propertyGbLimitMin;
    }

    public String getType() {
        return type;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public String getCity() {
        return city;
    }

    public String getProperty() {
        return property;
    }

    public String getPropertyColor() {
        return propertyColor;
    }

    public Integer getPropertyGbLimitMax() {
        return propertyGbLimitMax;
    }

    public Integer getPropertyGbLimitMin() {
        return propertyGbLimitMin;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private Double minPrice;
        private Double maxPrice;
        private String city;
        private String property;
        private String propertyColor;
        private Integer propertyGbLimitMax;
        private Integer propertyGbLimitMin;

        private Builder() {
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withMaxPrice(Double maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public Builder withMinPrice(Double minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withProperty(String property) {
            this.property = property;
            return this;
        }

        public Builder withPropertyColor(String propertyColor) {
            this.propertyColor = propertyColor;
            return this;
        }

        public Builder withPropertyGbLimitMax(Integer propertyGbLimitMax) {
            this.propertyGbLimitMax = propertyGbLimitMax;
            return this;
        }

        public Builder withPropertyGbLimitMin(Integer propertyGbLimitMin) {
            this.propertyGbLimitMin = propertyGbLimitMin;
            return this;
        }

        public ProductSearchParameters build() {
            return new ProductSearchParameters(this);
        }
    }
}
